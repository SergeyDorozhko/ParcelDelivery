package org.darozhka.parceldelivery.delivery.replicator;

import org.darozhka.parceldelivery.kafka.event.EventHandler;
import org.darozhka.parceldelivery.kafka.event.UserEvent;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author S.Darozhka
 */
@Transactional
public interface UserReplicator extends EventHandler<UserEvent> {
}
