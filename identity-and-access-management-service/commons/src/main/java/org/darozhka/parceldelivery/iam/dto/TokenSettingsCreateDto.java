package org.darozhka.parceldelivery.iam.dto;


import java.time.Duration;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

public class TokenSettingsCreateDto {

    private String accessTokenFormat;
    private Long authorizationCodeTimeToLive;
    private Long accessTokenTimeToLive;
    private Long refreshTokenTimeToLive;
    private Boolean reuseRefreshTokens;

    public String getAccessTokenFormat() {
        return accessTokenFormat;
    }

    public void setAccessTokenFormat(String accessTokenFormat) {
        this.accessTokenFormat = accessTokenFormat;
    }

    public Long getAuthorizationCodeTimeToLive() {
        return authorizationCodeTimeToLive;
    }

    public void setAuthorizationCodeTimeToLive(Long authorizationCodeTimeToLive) {
        this.authorizationCodeTimeToLive = authorizationCodeTimeToLive;
    }

    public Long getAccessTokenTimeToLive() {
        return accessTokenTimeToLive;
    }

    public void setAccessTokenTimeToLive(Long accessTokenTimeToLive) {
        this.accessTokenTimeToLive = accessTokenTimeToLive;
    }

    public Long getRefreshTokenTimeToLive() {
        return refreshTokenTimeToLive;
    }

    public void setRefreshTokenTimeToLive(Long refreshTokenTimeToLive) {
        this.refreshTokenTimeToLive = refreshTokenTimeToLive;
    }

    public Boolean getReuseRefreshTokens() {
        return reuseRefreshTokens;
    }

    public void setReuseRefreshTokens(Boolean reuseRefreshTokens) {
        this.reuseRefreshTokens = reuseRefreshTokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TokenSettingsCreateDto that = (TokenSettingsCreateDto) o;
        return Objects.equals(accessTokenFormat, that.accessTokenFormat) &&
                Objects.equals(authorizationCodeTimeToLive, that.authorizationCodeTimeToLive) &&
                Objects.equals(accessTokenTimeToLive, that.accessTokenTimeToLive) &&
                Objects.equals(refreshTokenTimeToLive, that.refreshTokenTimeToLive) &&
                Objects.equals(reuseRefreshTokens, that.reuseRefreshTokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                accessTokenFormat,
                authorizationCodeTimeToLive,
                accessTokenTimeToLive,
                refreshTokenTimeToLive,
                reuseRefreshTokens);
    }

    @Override
    public String toString() {
        return "TokenSettingsCreateDto{" +
                "accessTokenFormat='" + accessTokenFormat + '\'' +
                ", authorizationCodeTimeToLive=" + authorizationCodeTimeToLive +
                ", accessTokenTimeToLive=" + accessTokenTimeToLive +
                ", refreshTokenTimeToLive=" + refreshTokenTimeToLive +
                ", reuseRefreshTokens=" + reuseRefreshTokens +
                '}';
    }

    public TokenSettings toTokenSettings(){
        TokenSettings.Builder builder = TokenSettings.builder();

        if (StringUtils.isNotBlank(accessTokenFormat)) {
            builder.accessTokenFormat(new OAuth2TokenFormat(accessTokenFormat));
        }
        if (Objects.nonNull(accessTokenTimeToLive)) {
            builder.accessTokenTimeToLive(Duration.ofSeconds(accessTokenTimeToLive));
        }
        if (Objects.nonNull(authorizationCodeTimeToLive)) {
            builder.authorizationCodeTimeToLive(Duration.ofSeconds(authorizationCodeTimeToLive));
        }
        if (Objects.nonNull(refreshTokenTimeToLive)) {
            builder.refreshTokenTimeToLive(Duration.ofSeconds(refreshTokenTimeToLive));
        }
        if (Objects.nonNull(reuseRefreshTokens)) {
            builder.reuseRefreshTokens(reuseRefreshTokens);
        }

        return builder.build();
    }
}

