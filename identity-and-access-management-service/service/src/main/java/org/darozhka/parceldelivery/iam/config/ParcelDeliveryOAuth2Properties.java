package org.darozhka.parceldelivery.iam.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author S.Darozhka
 */
@ConfigurationProperties(prefix = "parceldelivery.iam.oauth2")
public class ParcelDeliveryOAuth2Properties {

    private String issuer;
    private String authorizationEndpoint;
    private String tokenEndpoint;
    private String tokenRevocationEndpoint;
    private String tokenIntrospectionEndpoint;
    private String jwkSetEndpoint;
    private String oidcClientRegistrationEndpoint;
    private String oidcUserInfoEndpoint;
    private JksCertificate jksCertificate;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAuthorizationEndpoint() {
        return authorizationEndpoint;
    }

    public void setAuthorizationEndpoint(String authorizationEndpoint) {
        this.authorizationEndpoint = authorizationEndpoint;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public String getTokenRevocationEndpoint() {
        return tokenRevocationEndpoint;
    }

    public void setTokenRevocationEndpoint(String tokenRevocationEndpoint) {
        this.tokenRevocationEndpoint = tokenRevocationEndpoint;
    }

    public String getTokenIntrospectionEndpoint() {
        return tokenIntrospectionEndpoint;
    }

    public void setTokenIntrospectionEndpoint(String tokenIntrospectionEndpoint) {
        this.tokenIntrospectionEndpoint = tokenIntrospectionEndpoint;
    }

    public String getJwkSetEndpoint() {
        return jwkSetEndpoint;
    }

    public void setJwkSetEndpoint(String jwkSetEndpoint) {
        this.jwkSetEndpoint = jwkSetEndpoint;
    }

    public String getOidcClientRegistrationEndpoint() {
        return oidcClientRegistrationEndpoint;
    }

    public void setOidcClientRegistrationEndpoint(String oidcClientRegistrationEndpoint) {
        this.oidcClientRegistrationEndpoint = oidcClientRegistrationEndpoint;
    }

    public String getOidcUserInfoEndpoint() {
        return oidcUserInfoEndpoint;
    }

    public void setOidcUserInfoEndpoint(String oidcUserInfoEndpoint) {
        this.oidcUserInfoEndpoint = oidcUserInfoEndpoint;
    }

    public JksCertificate getJksCertificate() {
        return jksCertificate;
    }

    public void setJksCertificate(JksCertificate jksCertificate) {
        this.jksCertificate = jksCertificate;
    }

    public static class JksCertificate {

        private String filePath;
        private String password;
        private String alias;

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }
    }
}
