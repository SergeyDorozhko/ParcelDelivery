package org.darozhka.parceldelivery.kafka.event;

/**
 * @author S.Darozhka
 */
public interface KafkaProducer {

    <T extends Event> void send(T event);
}
