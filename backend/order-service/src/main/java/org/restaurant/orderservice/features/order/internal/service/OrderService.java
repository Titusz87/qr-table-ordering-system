package org.restaurant.orderservice.features.order.internal.service;

import org.restaurant.orderservice.features.order.dto.CreateOrderRequest;
import org.restaurant.orderservice.features.order.dto.GetOrdersRequest;
import org.restaurant.orderservice.features.order.internal.model.Order;


import java.util.List;

public interface OrderService {
     Order createOrder (CreateOrderRequest request);
     List<Order> listOrders(GetOrdersRequest request);
}
