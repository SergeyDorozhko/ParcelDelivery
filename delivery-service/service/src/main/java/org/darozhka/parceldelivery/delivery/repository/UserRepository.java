package org.darozhka.parceldelivery.delivery.repository;

import org.darozhka.parceldelivery.delivery.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author S.Darozhka
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
