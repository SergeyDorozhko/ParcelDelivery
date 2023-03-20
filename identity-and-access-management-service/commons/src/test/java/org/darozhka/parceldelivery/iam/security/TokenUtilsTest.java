package org.darozhka.parceldelivery.iam.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.darozhka.parceldelivery.iam.domain.ParcelDeliveryUserDetails;
import org.darozhka.parceldelivery.iam.domain.SecurityRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author S.Darozhka
 */
class TokenUtilsTest {

    private static final int ID = 1;
    private static final String USERNAME = "Test";

    @Test
    public void testCustomizeClaimsApplicationLevel() {
        //Before
        JwtEncodingContext context =
                JwtEncodingContext.with(JwsHeader.with(SignatureAlgorithm.RS256), JwtClaimsSet.builder())
                        .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                        .principal(getAuthentication(SecurityRole.ADMIN))
                        .build();
        context.getClaims().claim("sub", USERNAME);

        //Test
        TokenUtils.customizeClaims(context);

        //Then
        ClaimAccessor token = context.getClaims().build();
        Assertions.assertEquals(SecurityRole.ADMIN, TokenUtils.getRole(token));
        Assertions.assertEquals(USERNAME, TokenUtils.getUsername(token));
    }

    private static Authentication getAuthentication(SecurityRole securityRole) {

        return new UsernamePasswordAuthenticationToken(
                new ParcelDeliveryUserDetails(
                        ID,
                        USERNAME,
                        StringUtils.EMPTY,
                        Boolean.TRUE,
                        Collections.singleton(securityRole)
                ),
                null);
    }

}