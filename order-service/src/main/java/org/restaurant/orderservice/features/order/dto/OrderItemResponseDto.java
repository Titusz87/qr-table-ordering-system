package org.restaurant.orderservice.features.order.dto;

import java.math.BigDecimal;

public record OrderItemResponseDto(
        String dishName,
        Integer quantity,
        BigDecimal price
) {
}
