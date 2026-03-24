package org.restaurant.orderservice.features.order.dto;

import jakarta.validation.constraints.NotBlank;

public record GetOrdersRequestDto(
        @NotBlank(message=ERROR_MESSAGE_EMAIL)
        String email,
        Boolean confirmed
) {
    private static final String ERROR_MESSAGE_EMAIL ="Email address must be provided";
}
