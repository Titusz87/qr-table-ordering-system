package org.restaurant.orderservice.features.user.internal.repository;

import org.restaurant.orderservice.features.user.internal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);
}