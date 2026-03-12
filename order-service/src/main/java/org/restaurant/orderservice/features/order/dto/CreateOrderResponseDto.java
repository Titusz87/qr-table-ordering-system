package org.restaurant.orderservice.features.order.dto;

import org.restaurant.orderservice.features.order.internal.model.OrderStatus;
import org.restaurant.orderservice.features.order.internal.model.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateOrderResponseDto(
        UUID id,
        String userName,
        List<OrderItemResponseDto> items,
        LocalDateTime placedAt,
        PaymentType type,
        String comment,
        OrderStatus status,
        BigDecimal total
) {

}
