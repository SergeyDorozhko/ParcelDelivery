package org.darozhka.parceldelivery.delivery.service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.Validate;
import org.darozhka.parceldelivery.delivery.domain.Order;
import org.darozhka.parceldelivery.delivery.domain.OrderStatus;
import org.darozhka.parceldelivery.delivery.domain.User;
import org.darozhka.parceldelivery.delivery.repository.AddressRepository;
import org.darozhka.parceldelivery.delivery.repository.OrderRepository;
import org.darozhka.parceldelivery.delivery.repository.UserRepository;
import org.darozhka.parceldelivery.iam.domain.SecurityRole;
import org.darozhka.parceldelivery.iam.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author S.Darozhka
 */
public class OrderServiceImpl implements OrderService {

    private static final String ORDER_NOT_FOUND_MESSAGE = "Order nof found by id: ";
    private static final String ILLEGIBLE_STATUS_FOR_MESSAGE = "Illegible status for %";
    private static final String UNKNOWN_ROLE_TYPE = "Unknown Role type: ";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Order getOrderById(Integer orderId) {
        Validate.notNull(orderId, "Order id is null");

        switch (SecurityUtils.getUserRole()) {
            case USER:
                return orderRepository.getByIdAndOwnerId(orderId, SecurityUtils.getUserId());
            case COURIER:
                return orderRepository.getByIdAndCourierId(orderId, SecurityUtils.getUserId());
            case ADMIN:
                return orderRepository.getReferenceById(orderId);
            default:
                throw new IllegalArgumentException(UNKNOWN_ROLE_TYPE + SecurityUtils.getUserRole());
        }
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        Validate.notNull(pageable, "Pageable is null");
        switch (SecurityUtils.getUserRole()) {
            case USER:
                return orderRepository.findAllByOwnerId(SecurityUtils.getUserId(), pageable);
            case COURIER:
                return orderRepository.findAllByCourierId(SecurityUtils.getUserId(), pageable);
            case ADMIN:
                return orderRepository.findAll(pageable);
            default:
                throw new IllegalArgumentException(UNKNOWN_ROLE_TYPE + SecurityUtils.getUserRole());
        }
    }

    @Override
    public Order save(Order order) {
        Validate.notNull(order, "Order is null");

        addressRepository.saveAndFlush(order.getPickUpAddress());
        addressRepository.saveAndFlush(order.getDestinationAddress());

        if (Objects.isNull(order.getId())) {
            return createOrder(order);
        } else {
            return updateOrder(order);
        }
    }

    private Order createOrder(Order order) {
        order.setOwner(new User());
        order.getOwner().setId(SecurityUtils.getUserId());
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setCreatedBy(SecurityUtils.getUsername());
        order.setLastModifiedAt(LocalDateTime.now());
        order.setLastModifiedBy(SecurityUtils.getUsername());

        return orderRepository.saveAndFlush(order);
    }

    private Order updateOrder(Order order) {
        Order oldOrder =
                orderRepository.getByIdAndOwnerId(order.getId(), SecurityUtils.getUserId());

        Validate.isTrue(OrderStatus.isUpdateAllowed(oldOrder.getStatus()), "Update order with status '%s' is not allowed", oldOrder.getStatus());

        oldOrder.setStatus(OrderStatus.UPDATED);
        oldOrder.setDeliveryDay(order.getDeliveryDay());
        oldOrder.setPickUpAddress(order.getPickUpAddress());
        oldOrder.setDestinationAddress(order.getDestinationAddress());
        oldOrder.setDescription(order.getDescription());
        oldOrder.setReceiverContact(order.getReceiverContact());
        oldOrder.setLastModifiedBy(SecurityUtils.getUsername());
        oldOrder.setLastModifiedAt(LocalDateTime.now());

        return orderRepository.saveAndFlush(oldOrder);
    }

    @Override
    public void updateOrderStatus(Integer orderId, OrderStatus orderStatus) {
        Validate.notNull(orderId, "orderId is null");
        validateOrderStatus(orderStatus);

        Order order = Optional.ofNullable(getOrderById(orderId))
                .orElseThrow(() -> new EntityNotFoundException(ORDER_NOT_FOUND_MESSAGE + orderId));

        order.setStatus(orderStatus);
        order.setLastModifiedBy(SecurityUtils.getUsername());
        order.setLastModifiedAt(LocalDateTime.now());

        orderRepository.saveAndFlush(order);
    }

    private void validateOrderStatus(OrderStatus orderStatus) {
        final SecurityRole currentUserRole = SecurityUtils.getUserRole();
        Set<OrderStatus> validStatuses = OrderStatus.MANUAL_STATUSES.get(currentUserRole);

        Validate.notNull(validStatuses, UNKNOWN_ROLE_TYPE + currentUserRole);
        Validate.isTrue(validStatuses.contains(orderStatus), ILLEGIBLE_STATUS_FOR_MESSAGE + currentUserRole);
    }

    @Override
    public void addOrderCourier(Integer orderId, Integer courierId) {
        Validate.notNull(orderId, "Order id is null");
        Validate.notNull(courierId, "Courier id is null");

        Order order = orderRepository.getReferenceById(orderId);

        User courier = userRepository.findById(courierId)
                .filter(user -> Objects.equals(user.getRole(), SecurityRole.COURIER))
                .orElseThrow(() -> new EntityNotFoundException("Courier not found by id " + orderId));

        order.setCourier(courier);
        order.setStatus(OrderStatus.COURIER_ASSIGNED);
        order.setLastModifiedBy(SecurityUtils.getUsername());
        order.setLastModifiedAt(LocalDateTime.now());

        orderRepository.saveAndFlush(order);
    }

}
