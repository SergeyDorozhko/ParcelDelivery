package org.darozhka.parceldelivery.iam.service;


import org.darozhka.parceldelivery.iam.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author S.Darozhka
 */
public interface UserService {

    User getById(Integer id);

    User getByUsername(String username);

    @PreAuthorize(
            "hasPermission(null, T(org.darozhka.parceldelivery.iam.domain.SecurityRole).USER) or " +
            "hasPermission(null, T(org.darozhka.parceldelivery.iam.domain.SecurityRole).ADMIN)")
    User save(User user);

    User registerUser(User user);
}
