package org.restaurant.orderservice.features.order.internal.repository;

import org.restaurant.orderservice.features.order.internal.model.Order;
import org.restaurant.orderservice.features.order.internal.model.OrderStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDao extends JpaRepository<Order, UUID> {
   // @EntityGraph: Fetches all entities needed at once when session is open to avoid lazy loading issues
   @EntityGraph(attributePaths = {"user", "items", "items.dish"})
   List<Order> findAllByUserEmail(String email);

   @EntityGraph(attributePaths = {"user", "items", "items.dish"})
   List<Order> findAllByUserEmailAndStatus(String email, OrderStatus status);

}