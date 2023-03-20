package org.darozhka.parceldelivery.delivery.config;

import org.darozhka.parceldelivery.delivery.replicator.UserReplicator;
import org.darozhka.parceldelivery.delivery.replicator.UserReplicatorImpl;
import org.darozhka.parceldelivery.delivery.service.OrderService;
import org.darozhka.parceldelivery.delivery.service.OrderServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author S.Darozhka
 */
@Configuration
@EntityScan(basePackages = "org.darozhka.parceldelivery.delivery")
public class ParcelDeliveryServiceConfiguration {

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }

    @Bean
    public UserReplicator userReplicator() {
        return new UserReplicatorImpl();
    }
}
