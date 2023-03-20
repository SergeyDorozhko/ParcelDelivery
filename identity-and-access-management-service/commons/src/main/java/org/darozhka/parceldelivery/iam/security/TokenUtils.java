package org.darozhka.parceldelivery.iam.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.darozhka.parceldelivery.iam.domain.ParcelDeliveryUserDetails;
import org.darozhka.parceldelivery.iam.domain.SecurityRole;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;

/**
 * @author S.Darozhka
 */
public class TokenUtils {

    private static final String ID_CLAIM = "id";
    private static final String ROLE_CLAIM = "role";
    private static final String SUBJECT_CLAIM = "sub";

    private static final Set<String> CUSTOMISABLE_TOKENS;

    static {
        Set<String> customisableTokens = new HashSet<>();
        customisableTokens.add("id_token");
        customisableTokens.add("access_token");

        CUSTOMISABLE_TOKENS = Collections.unmodifiableSet(customisableTokens);
    }

    public static <T extends JwtEncodingContext> void customizeClaims(T context) {
        Validate.notNull(context, "OAuth2TokenContext is null");

        if (CUSTOMISABLE_TOKENS.contains(context.getTokenType().getValue()) &&
                context.getPrincipal().getPrincipal() instanceof ParcelDeliveryUserDetails) {
            ParcelDeliveryUserDetails userDetails = (ParcelDeliveryUserDetails) context.getPrincipal().getPrincipal();

            JwtClaimsSet.Builder claims = context.getClaims();

            claims.claim(ID_CLAIM, userDetails.getId());

            if (CollectionUtils.isNotEmpty(userDetails.getAuthorities())) {
                claims.claim(ROLE_CLAIM, userDetails.getAuthorities().iterator().next().getAuthority());
            }
        }

    }

    public static SecurityRole getRole(ClaimAccessor token) {
        String roleClaim = token.getClaim(ROLE_CLAIM);

        return Arrays.stream(SecurityRole.values())
                .filter(role -> Objects.equals(role.getAuthority(),roleClaim))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown role"));
    }

    public static String getUsername(ClaimAccessor token) {
        return token.getClaim(SUBJECT_CLAIM);
    }

    public static Integer getUserId(ClaimAccessor token) {
        return Long.valueOf((long)token.getClaim(ID_CLAIM))
                .intValue();
    }

    private TokenUtils() {
    }
}
