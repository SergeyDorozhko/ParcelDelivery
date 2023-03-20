package org.darozhka.parceldelivery.iam.security;

import java.util.Collections;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.darozhka.parceldelivery.iam.domain.ParcelDeliveryUserDetails;
import org.darozhka.parceldelivery.iam.domain.SecurityRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.ClaimAccessor;

/**
 * @author S.Darozhka
 */
public abstract class SecurityUtils {

    private static final String SYSTEM_PRINCIPAL = "parcel_delivery";

    public static SecurityRole getUserRole() {
        Object principal = getPrincipal();
        if (principal instanceof ClaimAccessor) {
            return  TokenUtils.getRole((ClaimAccessor) principal);
        }
        if (principal instanceof ParcelDeliveryUserDetails) {
            return (SecurityRole)((ParcelDeliveryUserDetails) principal).getAuthorities().iterator().next();
        }

        throw new RuntimeException("Invalid authentication");
    }

    public static String getUsername() {
        Object principal = getPrincipal();
        if (principal instanceof ClaimAccessor) {
            return  TokenUtils.getUsername((ClaimAccessor) principal);
        }
        if (principal instanceof ParcelDeliveryUserDetails) {
            return ((ParcelDeliveryUserDetails) principal).getUsername();
        }

        throw new RuntimeException("Invalid authentication");
    }

    public static Integer getUserId() {

        Object principal = getPrincipal();
        if (principal instanceof ClaimAccessor) {
            return  TokenUtils.getUserId((ClaimAccessor) principal);
        }
        if (principal instanceof ParcelDeliveryUserDetails) {
            return ((ParcelDeliveryUserDetails) principal).getId();
        }

        throw new RuntimeException("Invalid authentication");
    }

    private static Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void doInSystemSecurityContext(Runnable runnable) {
        doInSecurityContext(runnable, createSystemSecurityContext());
    }

    private static void doInSecurityContext(Runnable runnable, SecurityContext securityContext) {
        Validate.notNull(runnable, "Runnable is null");

        SecurityContext originalSecurityContext = SecurityContextHolder.getContext();
        try {
            SecurityContextHolder.setContext(securityContext);
            runnable.run();
        } finally {
            SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
            if (emptyContext.equals(originalSecurityContext)) {
                SecurityContextHolder.clearContext();
            } else {
                SecurityContextHolder.setContext(originalSecurityContext);
            }
        }
    }

    public static SecurityContext createSystemSecurityContext() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(createSystemAuthentication());

        return securityContext;
    }

    public static Authentication createSystemAuthentication() {
        return new UsernamePasswordAuthenticationToken(
                new ParcelDeliveryUserDetails(
                        Integer.MAX_VALUE,
                        SYSTEM_PRINCIPAL,
                        StringUtils.EMPTY,
                        Boolean.TRUE,
                        Collections.singleton(SecurityRole.ADMIN)),
                StringUtils.EMPTY);
    }

    private SecurityUtils() {
    }
}
