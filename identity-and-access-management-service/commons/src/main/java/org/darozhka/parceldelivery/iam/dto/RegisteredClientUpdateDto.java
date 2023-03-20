package org.darozhka.parceldelivery.iam.dto;


import java.time.Instant;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

public class RegisteredClientUpdateDto {

    private String clientId;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;
    private Set<AuthorizationGrantType> authorizationGrantTypes;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private TokenSettingsCreateDto tokenSettings;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Instant getClientSecretExpiresAt() {
        return clientSecretExpiresAt;
    }

    public void setClientSecretExpiresAt(Instant clientSecretExpiresAt) {
        this.clientSecretExpiresAt = clientSecretExpiresAt;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Set<ClientAuthenticationMethod> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(Set<ClientAuthenticationMethod> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public Set<AuthorizationGrantType> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(Set<AuthorizationGrantType> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    public TokenSettingsCreateDto getTokenSettings() {
        return tokenSettings;
    }

    public void setTokenSettings(TokenSettingsCreateDto tokenSettings) {
        this.tokenSettings = tokenSettings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegisteredClientUpdateDto that = (RegisteredClientUpdateDto) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(clientSecretExpiresAt, that.clientSecretExpiresAt) &&
                Objects.equals(clientName, that.clientName) &&
                Objects.equals(clientAuthenticationMethods, that.clientAuthenticationMethods) &&
                Objects.equals(authorizationGrantTypes, that.authorizationGrantTypes) &&
                Objects.equals(redirectUris, that.redirectUris) &&
                Objects.equals(scopes, that.scopes) &&
                Objects.equals(tokenSettings, that.tokenSettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                clientId,
                clientSecretExpiresAt,
                clientName,
                clientAuthenticationMethods,
                authorizationGrantTypes,
                redirectUris,
                scopes,
                tokenSettings);
    }

    @Override
    public String toString() {
        return "RegisteredClientUpdateDto{" +
                "clientId='" + clientId + '\'' +
                ", clientSecretExpiresAt=" + clientSecretExpiresAt +
                ", clientName='" + clientName + '\'' +
                ", clientAuthenticationMethods=" + clientAuthenticationMethods +
                ", authorizationGrantTypes=" + authorizationGrantTypes +
                ", redirectUris=" + redirectUris +
                ", scopes=" + scopes +
                ", tokenSettings=" + tokenSettings +
                '}';
    }

    public RegisteredClient toRegisteredClient(String id) {
        RegisteredClient.Builder builder = RegisteredClient
                .withId(id)
                .clientId(clientId);

        if (Objects.nonNull(clientSecretExpiresAt)) {
            builder.clientSecretExpiresAt(clientSecretExpiresAt);
        }
        if (StringUtils.isNotBlank(clientName)) {
            builder.clientName(clientName);
        }
        if (CollectionUtils.isNotEmpty(clientAuthenticationMethods)) {
            for (ClientAuthenticationMethod authenticationMethod : clientAuthenticationMethods) {
                builder.clientAuthenticationMethod(authenticationMethod);
            }
        }
        if (CollectionUtils.isNotEmpty(authorizationGrantTypes)) {
            for (AuthorizationGrantType grantType : authorizationGrantTypes) {
                builder.authorizationGrantType(grantType);
            }
        }
        if (CollectionUtils.isNotEmpty(redirectUris)) {
            for (String uri : redirectUris) {
                builder.redirectUri(uri);
            }
        }
        if (CollectionUtils.isNotEmpty(scopes)) {
            for (String scope : scopes) {
                builder.scope(scope);
            }
        }
        if (Objects.nonNull(tokenSettings)) {
            builder.tokenSettings(tokenSettings.toTokenSettings());
        }

        return builder.build();
    }
}

