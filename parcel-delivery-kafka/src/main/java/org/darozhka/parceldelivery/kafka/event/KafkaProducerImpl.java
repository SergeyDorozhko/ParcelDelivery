package org.darozhka.parceldelivery.kafka.event;

import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author S.Darozhka
 */
public class KafkaProducerImpl implements KafkaProducer {

    private final String topic;

    private KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerImpl(String topic, KafkaTemplate<String, Object> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public <T extends Event> void send(T event) {
        kafkaTemplate.send(topic, event);
    }
}
