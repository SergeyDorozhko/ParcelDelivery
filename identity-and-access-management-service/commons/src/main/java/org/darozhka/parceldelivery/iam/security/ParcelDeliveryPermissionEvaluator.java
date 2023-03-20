package org.darozhka.parceldelivery.iam.security;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.Validate;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

/**
 * @author S.Darozhka
 */
public class ParcelDeliveryPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject,
                                 Object permission) {
        Validate.notNull(permission, "Permission is null.");

        return Objects.equals(permission, SecurityUtils.getUserRole());
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        return false;
    }

}
