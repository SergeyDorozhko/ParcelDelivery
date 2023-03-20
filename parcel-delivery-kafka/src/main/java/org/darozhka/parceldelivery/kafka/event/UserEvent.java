package org.darozhka.parceldelivery.kafka.event;

import org.darozhka.parceldelivery.commons.domain.Entity;

/**
 * @author S.Darozhka
 */
public class UserEvent extends EntityEvent {

    public UserEvent() {
    }

    public UserEvent(Entity entity) {
        super(entity);
    }

}
