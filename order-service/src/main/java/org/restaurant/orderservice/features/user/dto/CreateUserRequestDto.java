package org.restaurant.orderservice.features.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDto(
        @NotBlank(message=ERROR_MESSAGE_FIRST_NAME)
        String firstName,
        @NotBlank(message=ERROR_MESSAGE_LAST_NAME)
        String lastName,
        @NotBlank(message=ERROR_MESSAGE_USER_NAME)
        String userName,
        @NotBlank(message=ERROR_MESSAGE_EMAIL)
        String email,
        @NotBlank(message=ERROR_MESSAGE_PHONE)
        String phone,
        @NotBlank(message=ERROR_MESSAGE_ADDRESS)
        String address
) {
    private static final String ERROR_MESSAGE_FIRST_NAME = "First name must be provided";
    private static final String ERROR_MESSAGE_LAST_NAME = "Last name must be provided";
    private static final String ERROR_MESSAGE_USER_NAME = "User name must be provided";
    private static final String ERROR_MESSAGE_EMAIL = "Email address must be provided";
    private static final String ERROR_MESSAGE_PHONE = "Phone number must be provided";
    private static final String ERROR_MESSAGE_ADDRESS = "Address details must be provided";
}
