package org.darozhka.parceldelivery.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author S.Darozhka
 */
@SpringBootApplication(scanBasePackages = "org.darozhka.parceldelivery.delivery")
public class ParcelDeliveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParcelDeliveryServiceApplication.class);
    }
}
