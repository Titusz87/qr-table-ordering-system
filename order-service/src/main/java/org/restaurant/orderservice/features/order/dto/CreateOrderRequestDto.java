package org.restaurant.orderservice.features.order.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.restaurant.orderservice.features.order.internal.model.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

public record CreateOrderRequestDto(
        @NotBlank(message=ERROR_MESSAGE_USER_NAME)
        String userName,
        @NotNull(message=ERROR_MESSAGE_EMPTY_LIST)
        List<OrderItemRequestDto> items,
        LocalDateTime placedAt,
        @NotNull(message=ERROR_MESSAGE_PAYMENT_NOT_SELECTED)
        PaymentType paymentType,
        @Nullable
        String comment
) {
        private static final String ERROR_MESSAGE_USER_NAME="User name must be provided";
        private static final String ERROR_MESSAGE_EMPTY_LIST="Error: There are no items in the basket.";
        private static final String ERROR_MESSAGE_PAYMENT_NOT_SELECTED="Please select a payment method.";
}
