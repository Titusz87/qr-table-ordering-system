package org.restaurant.orderservice.features.order.internal.service.impl;

import org.restaurant.orderservice.features.menu.internal.model.Dish;
import org.restaurant.orderservice.features.order.api.CreateOrderRequest;
import org.restaurant.orderservice.features.order.dto.GetOrdersRequest;
import org.restaurant.orderservice.features.order.internal.model.Order;
import org.restaurant.orderservice.features.order.internal.model.OrderItem;
import org.restaurant.orderservice.features.order.internal.model.OrderStatus;
import org.restaurant.orderservice.features.user.internal.model.User;
import org.restaurant.orderservice.features.menu.internal.repository.MenuDao;
import org.restaurant.orderservice.features.order.internal.repository.OrderDao;
import org.restaurant.orderservice.features.order.internal.service.OrderService;
import org.restaurant.orderservice.features.user.internal.repository.UserDao;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final MenuDao menuDao;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, MenuDao menuDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.menuDao = menuDao;
    }

    @Override
    public Order createOrder(CreateOrderRequest request) {
        LocalDateTime now = LocalDateTime.now();
        User user = userDao.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found with user name: " + request.getUserName()));

        Order order = new Order(
                null,
                now,
                new ArrayList<>(),
                user,
                OrderStatus.CONFIRMED,
                request.getPaymentType(),
                request.getComment()
        );

        List<OrderItem> mappedItems = request.getItems().stream()
                .map(item -> {
                    Dish dish = menuDao.findByDishName(item.dishName())
                            .orElseThrow(() ->
                                    new RuntimeException("Dish not found: " + item.dishName()));
                    return new OrderItem(order, dish, item.quantity());
                })
                .toList();

        order.setItems(mappedItems);
        order.setTotal(order.calculateTotal());

        return orderDao.save(order);

    }
    @Override
    public List<Order> listOrders(GetOrdersRequest request) {
        List<Order> orders = new ArrayList<>();

        if(request.getConfirmed()){
            orders= orderDao.findAllByUserEmailAndStatus(request.getEmail(), OrderStatus.CONFIRMED);
        }
        if(!request.getConfirmed()){
            orders= orderDao.findAllByUserEmail(request.getEmail());

        }
        return orders;
    }
}