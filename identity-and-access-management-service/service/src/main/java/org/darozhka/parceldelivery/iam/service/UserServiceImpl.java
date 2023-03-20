package org.darozhka.parceldelivery.iam.service;

import java.util.Objects;

import javax.jws.soap.SOAPBinding;

import org.apache.commons.lang3.Validate;
import org.darozhka.parceldelivery.iam.domain.SecurityRole;
import org.darozhka.parceldelivery.iam.domain.User;
import org.darozhka.parceldelivery.iam.repository.UserRepository;
import org.darozhka.parceldelivery.iam.security.SecurityUtils;
import org.darozhka.parceldelivery.kafka.event.KafkaProducer;
import org.darozhka.parceldelivery.kafka.event.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author S.Darozhka
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getById(Integer id) {
        Validate.notNull(id, "Id is null");

        return userRepository.getReferenceById(id);
    }

    @Override
    public User getByUsername(String username) {
        Validate.notBlank(username, "Username is blank");

        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        Validate.notNull(user, "User is null.");

        if (Objects.isNull(user.getId())) {
            Validate.notBlank(user.getPassword(), "Password of user is null or blank");

            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            User oldUser = getById(user.getId());
            validateUserUpdate(oldUser);

            user.setPassword(oldUser.getPassword());
            user.setRole(oldUser.getRole());
        }

        User saved = userRepository.saveAndFlush(user); //todo aspect

        kafkaProducer.send(new UserEvent(saved));

        return saved;
    }

    private void validateUserUpdate(User user) {
        if (Objects.equals(SecurityUtils.getUserId(), user.getId())) {
            return;
        }
        if (Objects.equals(SecurityUtils.getUserRole(), SecurityRole.ADMIN)) {
            return;
        }

        throw new AccessDeniedException("User update not allowed");
    }

    @Override
    public User registerUser(User user) {
        Validate.notNull(user, "User is null");

        user.setRole(SecurityRole.USER);

        return save(user);
    }

}
