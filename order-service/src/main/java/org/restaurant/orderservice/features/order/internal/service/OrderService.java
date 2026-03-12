package org.restaurant.orderservice.features.order.internal.service;

import org.restaurant.orderservice.features.order.api.CreateOrderRequest;
import org.restaurant.orderservice.features.order.dto.GetOrdersRequest;
import org.restaurant.orderservice.features.order.internal.model.Order;


import java.util.List;

public interface OrderService {
     Order createOrder (CreateOrderRequest request);
     List<Order> listOrders(GetOrdersRequest request);
     //List<CreateOrderResponseDto> listOrder();
     //TODO: IMPLEMENT similar to -> void processingOrder()
     /*
     * after further validation it would:
     * - change the OrderStatus-> CONFIRMED
     */
     //TODO: IMPLEMENT similar to -> void estimateOrderCompletionTime()
     /*
     * based on dish attributes like: prep time and busyFactor (random between an intervallum)
     * would estimate the completionTime to ready to pick up.
     *
     */
}
