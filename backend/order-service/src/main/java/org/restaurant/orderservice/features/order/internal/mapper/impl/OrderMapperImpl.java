package org.restaurant.orderservice.features.order.internal.mapper.impl;

import org.restaurant.orderservice.features.order.dto.CreateOrderRequest;
import org.restaurant.orderservice.features.order.dto.*;
import org.restaurant.orderservice.features.order.internal.mapper.OrderMapper;
import org.restaurant.orderservice.features.order.internal.model.Order;
import org.restaurant.orderservice.features.order.internal.model.OrderItem;
import org.restaurant.orderservice.features.order.internal.model.OrderItemRequest;
import org.springframework.stereotype.Component;

import java.util.List;

// Maps between Order DTOs and domain models
@Component
public class OrderMapperImpl implements OrderMapper {
    // Converts incoming API request DTO into a service-layer CreateOrderRequest
    @Override
    public CreateOrderRequest fromDto(CreateOrderRequestDto request) {
        return new CreateOrderRequest(
                null,
                request.userName(),
                fromDto(request.items()),
                request.placedAt(),
                request.paymentType(),
                request.comment()
        );
    }

    // Converts Order domain object into API response DTO
    @Override
    public CreateOrderResponseDto toDto(Order order) {
        return new CreateOrderResponseDto (
                order.getId(),
                order.getUserName(),
                toDto(order.getItems()),
                order.getPlacedAt(),
                order.getPaymentType(),
                order.getComment(),
                order.getStatus(),
                order.getTotal()
        );
    }

    public List<OrderItemRequest> fromDto(List<OrderItemRequestDto> request) {
        return request.stream()
                .map(item -> new OrderItemRequest(
                        item.dishName(),
                        item.quantity()
                ))
                .toList();
    }

    @Override
    public List<OrderItemResponseDto> toDto(List<OrderItem> items) {
        return items.stream()
                .map(item -> new OrderItemResponseDto(
                        item.getDish().getDishName(),
                        item.getQuantity(),
                        item.getDish().getPrice()
                ))
                .toList();
    }

    @Override
    public GetOrdersRequest fromGetOrdersRequestDto(GetOrdersRequestDto request) {
        return new GetOrdersRequest(
                request.email(),
                request.confirmed()
        );
    }

    @Override
    public List<GetOrdersResponseDto> toGetOrdersResponseDto(List<Order> orders) {
        return orders.stream()
                .map(order -> new GetOrdersResponseDto(
                        order.getId(),
                        order.getUserName(),
                        toDto(order.getItems()),
                        order.getPlacedAt(),
                        order.getPaymentType(),
                        order.getComment(),
                        order.getStatus(),
                        order.getTotal()
                ))
                .toList();
    }
}
