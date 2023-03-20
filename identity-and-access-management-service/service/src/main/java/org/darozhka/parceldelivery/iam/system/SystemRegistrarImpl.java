package org.darozhka.parceldelivery.iam.system;

import java.util.Objects;

import org.apache.commons.lang3.Validate;
import org.darozhka.parceldelivery.iam.domain.SystemUser;
import org.darozhka.parceldelivery.iam.domain.User;
import org.darozhka.parceldelivery.iam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author S.Darozhka
 */
public class SystemRegistrarImpl implements SystemRegistrar {

    @Autowired
    private UserService userService;

    @Override
    public void registerUser(SystemUser systemUser) {
        Validate.notNull(systemUser, "System user can't be null");

        User user = userService.getByUsername(systemUser.getUsername());
        if (Objects.nonNull(user)) {
            return;
        }

        user = new User();
        user.setName(systemUser.getName());
        user.setSurname(systemUser.getSurname());
        user.setActive(systemUser.getActive());
        user.setUsername(systemUser.getUsername());
        user.setPassword(systemUser.getPassword());
        user.setRole(systemUser.getRole());

        userService.save(user);
    }
}
