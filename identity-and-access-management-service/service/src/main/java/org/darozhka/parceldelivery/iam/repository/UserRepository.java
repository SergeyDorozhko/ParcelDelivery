package org.darozhka.parceldelivery.iam.repository;


import org.darozhka.parceldelivery.iam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author S.Darozhka
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
