package org.darozhka.parceldelivery.kafka.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author S.Darozhka
 */
@ConfigurationProperties(prefix = "parceldelivery.kafka")
public class KafkaProperties {

    private String bootstrapAddress;

    private String topicName;

    private String consumerGroupId;

    private Integer numPartitions = 1;

    private Short replicationFactor = 1;

    public String getBootstrapAddress() {
        return bootstrapAddress;
    }

    public void setBootstrapAddress(String bootstrapAddress) {
        this.bootstrapAddress = bootstrapAddress;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getConsumerGroupId() {
        return consumerGroupId;
    }

    public void setConsumerGroupId(String consumerGroupId) {
        this.consumerGroupId = consumerGroupId;
    }

    public Integer getNumPartitions() {
        return numPartitions;
    }

    public void setNumPartitions(Integer numPartitions) {
        this.numPartitions = numPartitions;
    }

    public Short getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Short replicationFactor) {
        this.replicationFactor = replicationFactor;
    }
}
