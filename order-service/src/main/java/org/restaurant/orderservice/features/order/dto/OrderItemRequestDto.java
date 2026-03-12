package org.restaurant.orderservice.features.order.dto;

public record OrderItemRequestDto(
        String dishName,
        Integer quantity
) {
}
