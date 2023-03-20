package org.darozhka.parceldelivery.iam.config;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.darozhka.parceldelivery.commons.utils.TransactionHelper;
import org.darozhka.parceldelivery.iam.domain.ParcelDeliveryUserDetails;
import org.darozhka.parceldelivery.iam.domain.SystemUser;
import org.darozhka.parceldelivery.iam.security.SecurityUtils;
import org.darozhka.parceldelivery.iam.security.TokenUtils;
import org.darozhka.parceldelivery.iam.service.RegisteredClientService;
import org.darozhka.parceldelivery.iam.service.RegisteredClientServiceImpl;
import org.darozhka.parceldelivery.iam.service.UserService;
import org.darozhka.parceldelivery.iam.service.UserServiceImpl;
import org.darozhka.parceldelivery.iam.system.SystemRegistrar;
import org.darozhka.parceldelivery.iam.system.SystemRegistrarImpl;
import org.darozhka.parceldelivery.iam.utils.CertificateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * @author S.Darozhka
 */
@Configuration
@EntityScan("org.darozhka.parceldelivery")
@EnableConfigurationProperties(value = ParcelDeliveryOAuth2Properties.class)
public class ParcelDeliveryIdentityAndAccessManagementServiceConfiguration {

    @Autowired
    private ParcelDeliveryOAuth2Properties parcelDeliveryOAuth2Properties;

    @Autowired
    private TransactionHelper transactionHelper;

    @Autowired
    private SystemRegistrar systemRegistrar;

    @PostConstruct
    private void init() {
        for (SystemUser systemUser : SystemUser.values()) {
            SecurityUtils.doInSystemSecurityContext(() -> systemRegistrar.registerUser(systemUser));
        }
    }

    @Bean
    public SystemRegistrar systemRegistrar() {
        return new SystemRegistrarImpl();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        return http
                .formLogin()
                .and()
                .build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        AuthorizationServerSettings.Builder builder = AuthorizationServerSettings.builder();

        if (StringUtils.isNotBlank(parcelDeliveryOAuth2Properties.getIssuer())) {
            builder.issuer(parcelDeliveryOAuth2Properties.getIssuer());
        }
        if (StringUtils.isNotBlank(parcelDeliveryOAuth2Properties.getAuthorizationEndpoint())) {
            builder.authorizationEndpoint(parcelDeliveryOAuth2Properties.getAuthorizationEndpoint());
        }
        if (StringUtils.isNotBlank(parcelDeliveryOAuth2Properties.getTokenEndpoint())) {
            builder.tokenEndpoint(parcelDeliveryOAuth2Properties.getTokenEndpoint());
        }
        if (StringUtils.isNotBlank(parcelDeliveryOAuth2Properties.getTokenRevocationEndpoint())) {
            builder.tokenRevocationEndpoint(parcelDeliveryOAuth2Properties.getTokenRevocationEndpoint());
        }
        if (StringUtils.isNotBlank(parcelDeliveryOAuth2Properties.getTokenIntrospectionEndpoint())) {
            builder.tokenIntrospectionEndpoint(parcelDeliveryOAuth2Properties.getTokenIntrospectionEndpoint());
        }
        if (StringUtils.isNotBlank(parcelDeliveryOAuth2Properties.getOidcClientRegistrationEndpoint())) {
            builder.oidcClientRegistrationEndpoint(parcelDeliveryOAuth2Properties.getOidcClientRegistrationEndpoint());
        }
        if (StringUtils.isNotBlank(parcelDeliveryOAuth2Properties.getOidcUserInfoEndpoint())) {
            builder.oidcUserInfoEndpoint(parcelDeliveryOAuth2Properties.getOidcUserInfoEndpoint());
        }
        if (StringUtils.isNotBlank(parcelDeliveryOAuth2Properties.getJwkSetEndpoint())) {
            builder.jwkSetEndpoint(parcelDeliveryOAuth2Properties.getJwkSetEndpoint());
        }

        return builder.build();
    }

    @Bean
    @ConditionalOnMissingBean
    public JWKSource<SecurityContext> defaultJwkSource() {//todo implement certificate
        JWKSet set = new JWKSet(CertificateUtils.generateRsaKey());
        return ((jwkSelector, context) -> jwkSelector.select(set));
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return TokenUtils::customizeClaims;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public TransactionHelper transactionHelper() {
        return TransactionHelper.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return username ->
                transactionHelper().doInTransactionUnchecked(
                        () -> ParcelDeliveryUserDetails.getInstance(
                                Optional.ofNullable(userService.getByUsername(username))
                                        .orElseThrow(() -> new UsernameNotFoundException("User not found with username:" + username))));
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public RegisteredClientService registeredClientService() {
        return new RegisteredClientServiceImpl();
    }

}
