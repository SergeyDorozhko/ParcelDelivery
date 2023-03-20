package org.darozhka.parceldelivery.delivery.service;

import org.darozhka.parceldelivery.delivery.domain.Order;
import org.darozhka.parceldelivery.delivery.domain.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author S.Darozhka
 */
@Transactional(readOnly = true)
public interface OrderService {

    Order getOrderById(Integer orderId);

    Page<Order> getOrders(Pageable pageable);

    @Transactional(readOnly = false)
    @PreAuthorize("hasPermission(null, T(org.darozhka.parceldelivery.iam.domain.SecurityRole).USER)")
    Order save(Order order);

    @Transactional(readOnly = false)
    @PreAuthorize(
            "hasPermission(null, T(org.darozhka.parceldelivery.iam.domain.SecurityRole).USER) or " +
            "hasPermission(null, T(org.darozhka.parceldelivery.iam.domain.SecurityRole).ADMIN) or " +
            "hasPermission(null, T(org.darozhka.parceldelivery.iam.domain.SecurityRole).COURIER)")
    void updateOrderStatus(Integer orderId, OrderStatus orderStatus);

    @Transactional(readOnly = false)
    @PreAuthorize("hasPermission(null, T(org.darozhka.parceldelivery.iam.domain.SecurityRole).ADMIN)")
    void addOrderCourier(Integer orderId, Integer courierId);

}
