package org.darozhka.parceldelivery.iam.dto;


import java.util.Objects;
import java.util.Optional;

import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

public class TokenSettingsDto {

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
        TokenSettingsDto that = (TokenSettingsDto) o;
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

    public static TokenSettingsDto from(TokenSettings tokenSettings) {
        TokenSettingsDto dto = new TokenSettingsDto();

        dto.setAccessTokenFormat(
                Optional.ofNullable(tokenSettings.getAccessTokenFormat())
                        .map(OAuth2TokenFormat::getValue)
                        .orElse(null));
        dto.setAuthorizationCodeTimeToLive(tokenSettings.getAuthorizationCodeTimeToLive().getSeconds());
        dto.setAccessTokenTimeToLive(tokenSettings.getAccessTokenTimeToLive().getSeconds());
        dto.setRefreshTokenTimeToLive(tokenSettings.getRefreshTokenTimeToLive().getSeconds());
        dto.setReuseRefreshTokens(tokenSettings.isReuseRefreshTokens());

        return dto;
    }
}
