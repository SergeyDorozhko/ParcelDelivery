package org.darozhka.parceldelivery.iam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author S.Darozhka
 */
@SpringBootApplication(scanBasePackages = "org.darozhka.parceldelivery")
public class ParcelDeliveryIdentityAndAccessManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParcelDeliveryIdentityAndAccessManagementServiceApplication.class);
    }
}
