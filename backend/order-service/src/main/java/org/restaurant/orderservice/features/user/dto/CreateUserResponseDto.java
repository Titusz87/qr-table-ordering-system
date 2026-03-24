package org.restaurant.orderservice.features.user.dto;

public record CreateUserResponseDto(
        String firstName,
        String lastName,
        String userName,
        String email,
        String phone,
        String address
) {
}
