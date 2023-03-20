package org.darozhka.parceldelivery.delivery.repository;

import org.darozhka.parceldelivery.delivery.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author S.Darozhka
 */
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
