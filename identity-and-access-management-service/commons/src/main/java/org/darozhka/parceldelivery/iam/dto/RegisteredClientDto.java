package org.darozhka.parceldelivery.iam.dto;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

public class RegisteredClientDto {

    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;
    private Set<AuthorizationGrantType> authorizationGrantTypes;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private TokenSettingsDto tokenSettings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Instant getClientIdIssuedAt() {
        return clientIdIssuedAt;
    }

    public void setClientIdIssuedAt(Instant clientIdIssuedAt) {
        this.clientIdIssuedAt = clientIdIssuedAt;
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

    public TokenSettingsDto getTokenSettings() {
        return tokenSettings;
    }

    public void setTokenSettings(TokenSettingsDto tokenSettings) {
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
        RegisteredClientDto that = (RegisteredClientDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(clientIdIssuedAt, that.clientIdIssuedAt) &&
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
                id,
                clientId,
                clientIdIssuedAt,
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
        return "RegisteredClientDto{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientIdIssuedAt=" + clientIdIssuedAt +
                ", clientSecretExpiresAt=" + clientSecretExpiresAt +
                ", clientName='" + clientName + '\'' +
                ", clientAuthenticationMethods=" + clientAuthenticationMethods +
                ", authorizationGrantTypes=" + authorizationGrantTypes +
                ", redirectUris=" + redirectUris +
                ", scopes=" + scopes +
                ", tokenSettings=" + tokenSettings +
                '}';
    }

    public static RegisteredClientDto from(RegisteredClient registeredClient) {
        if (Objects.isNull(registeredClient)) {
            return null;
        }

        RegisteredClientDto dto = new RegisteredClientDto();

        dto.setId(registeredClient.getId());
        dto.setClientId(registeredClient.getClientId());
        dto.setClientName(registeredClient.getClientName());
        dto.setClientIdIssuedAt(registeredClient.getClientIdIssuedAt());
        dto.setClientSecretExpiresAt(registeredClient.getClientSecretExpiresAt());
        dto.setClientAuthenticationMethods(
                CollectionUtils.isEmpty(registeredClient.getClientAuthenticationMethods()) ?
                        Collections.emptySet() :
                        Collections.unmodifiableSet(new HashSet<>(registeredClient.getClientAuthenticationMethods())));
        dto.setAuthorizationGrantTypes(
                CollectionUtils.isEmpty(registeredClient.getAuthorizationGrantTypes()) ?
                        Collections.emptySet() :
                        Collections.unmodifiableSet(new HashSet<>(registeredClient.getAuthorizationGrantTypes())));
        dto.setRedirectUris(
                CollectionUtils.isEmpty(registeredClient.getRedirectUris()) ?
                        Collections.emptySet() :
                        Collections.unmodifiableSet(new HashSet<>(registeredClient.getRedirectUris())));
        dto.setScopes(
                CollectionUtils.isEmpty(registeredClient.getScopes()) ?
                        Collections.emptySet() :
                        Collections.unmodifiableSet(new HashSet<>(registeredClient.getScopes())));


        dto.setTokenSettings(TokenSettingsDto.from(registeredClient.getTokenSettings()));


        return dto;
    }
}

