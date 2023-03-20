package org.darozhka.parceldelivery.delivery.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.darozhka.parceldelivery.iam.domain.SecurityRole;


/**
 * @author S.Darozhka
 */
public enum OrderStatus {
    CREATED,
    UPDATED,
    CANCELED,
    COURIER_ASSIGNED,
    READY_FOR_DELIVERY,
    DELIVERY_IN_PROGRESS,
    FINISHED,
    CHANGES_REQUIRED;

    public static final boolean isUpdateAllowed(OrderStatus status) {
        return !MANUAL_STATUSES.get(SecurityRole.COURIER).contains(status);
    }

    public static final Map<SecurityRole, Set<OrderStatus>> MANUAL_STATUSES;

    static {
        Map<SecurityRole, Set<OrderStatus>> manualStatuses = new HashMap<>();

        Set<OrderStatus> userStatuses = new HashSet<>();
        userStatuses.add(OrderStatus.CANCELED);
        manualStatuses.put(SecurityRole.USER, userStatuses);

        Set<OrderStatus> adminStatuses = new HashSet<>();
        adminStatuses.add(OrderStatus.CANCELED);
        adminStatuses.add(OrderStatus.READY_FOR_DELIVERY);
        adminStatuses.add(OrderStatus.CHANGES_REQUIRED);
        adminStatuses.add(OrderStatus.FINISHED);
        manualStatuses.put(SecurityRole.ADMIN, adminStatuses);

        Set<OrderStatus> courierStatuses = new HashSet<>();
        courierStatuses.add(OrderStatus.DELIVERY_IN_PROGRESS);
        courierStatuses.add(OrderStatus.FINISHED);
        manualStatuses.put(SecurityRole.COURIER, courierStatuses);

        MANUAL_STATUSES = Collections.unmodifiableMap(manualStatuses);

    }
}
