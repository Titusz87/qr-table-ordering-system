package org.restaurant.orderservice.features.order.internal.mapper;

import org.restaurant.orderservice.features.order.api.CreateOrderRequest;
import org.restaurant.orderservice.features.order.dto.*;
import org.restaurant.orderservice.features.order.internal.model.Order;
import org.restaurant.orderservice.features.order.internal.model.OrderItem;
import org.restaurant.orderservice.features.order.internal.model.OrderItemRequest;
import java.util.List;

public interface OrderMapper {

    CreateOrderRequest fromDto(CreateOrderRequestDto request);

    CreateOrderResponseDto toDto(Order order);

    List<OrderItemRequest> fromDto(List<OrderItemRequestDto> dtos);

    List<OrderItemResponseDto> toDto(List<OrderItem> items);


//****************

    GetOrdersRequest fromGetOrdersRequestDto(GetOrdersRequestDto request);


    List<GetOrdersResponseDto> toGetOrdersResponseDto(List<Order> orders);



}
