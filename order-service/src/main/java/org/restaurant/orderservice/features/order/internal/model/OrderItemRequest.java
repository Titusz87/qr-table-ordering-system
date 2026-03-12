package org.restaurant.orderservice.features.order.internal.model;

public record OrderItemRequest(
        String dishName,
        Integer quantity
) {
}
