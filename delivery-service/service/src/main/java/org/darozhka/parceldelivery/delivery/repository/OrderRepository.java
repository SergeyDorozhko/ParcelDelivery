package org.darozhka.parceldelivery.delivery.repository;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.darozhka.parceldelivery.delivery.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author S.Darozhka
 */
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer>, JpaRepository<Order, Integer> {

    Optional<Order> findByIdAndOwnerId(Integer orderId, Integer ownerId);

    default Order getByIdAndOwnerId(Integer orderId, Integer ownerId) {
        return findByIdAndOwnerId(orderId, ownerId)
                .orElseThrow(()-> new EntityNotFoundException("Order not found by id: " + orderId));
    }

    Optional<Order> findByIdAndCourierId(Integer orderId, Integer courierId);

    default Order getByIdAndCourierId(Integer orderId, Integer ownerId) {
        return findByIdAndCourierId(orderId, ownerId)
                .orElseThrow(()-> new EntityNotFoundException("Order not found by id: " + orderId));
    }

    Page<Order> findAllByOwnerId(Integer ownerId, Pageable pageable);

    Page<Order> findAllByCourierId(Integer courierId, Pageable pageable);
}
