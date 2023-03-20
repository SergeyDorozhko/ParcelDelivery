package org.darozhka.parceldelivery.kafka.event;

import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author S.Darozhka
 */
public interface EventHandler<T extends Event> {

    @KafkaListener(topics = "${parceldelivery.kafka.topicName}", groupId = "${parceldelivery.kafka.consumerGroupId}")
    void handle(T event);
}
