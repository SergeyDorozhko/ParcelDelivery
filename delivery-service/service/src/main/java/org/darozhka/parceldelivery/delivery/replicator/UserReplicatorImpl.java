package org.darozhka.parceldelivery.delivery.replicator;

import java.util.Objects;

import org.darozhka.parceldelivery.commons.utils.MapperUtils;
import org.darozhka.parceldelivery.delivery.domain.User;
import org.darozhka.parceldelivery.delivery.repository.UserRepository;
import org.darozhka.parceldelivery.kafka.event.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author S.Darozhka
 */
public class UserReplicatorImpl implements UserReplicator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void handle(UserEvent userEvent) {
        if (Objects.isNull(userEvent)) {
            return;
        }

        User user = MapperUtils.fromJson(userEvent.getData(), User.class);

        userRepository.save(user);
    }
}
