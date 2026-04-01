package org.restaurant.orderservice.features.order.api;

import org.restaurant.orderservice.features.order.dto.*;
import org.restaurant.orderservice.features.order.internal.model.Order;
import org.restaurant.orderservice.features.order.internal.mapper.OrderMapper;
import org.restaurant.orderservice.features.order.internal.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * //@param orderService
 * //@param orderMapper
 *
 *
 */

//TODO: If user registered do not ask for details like address etc, but return it from db with the response.


@RestController
@RequestMapping(path="/api/v1/orders")
public class OrderControllerImpl {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderControllerImpl(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponseDto> createOrder(
            @Valid @RequestBody CreateOnlineOrderRequestDto createOnlineOrderRequestDto
    ) {
        CreateOrderRequest createOrderRequest = orderMapper.fromDto(createOnlineOrderRequestDto);
        Order createdOrder = orderService.createOrder(createOrderRequest);
        CreateOrderResponseDto response = orderMapper.toDto(createdOrder);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetOrdersResponseDto>> listOrderList(
        @Valid @RequestParam String email,
        @RequestParam(required = false) Boolean confirmed
    ) {
        GetOrdersRequestDto getOrdersRequestDto = new GetOrdersRequestDto(email,confirmed);

        GetOrdersRequest getOrdersRequest = orderMapper.fromGetOrdersRequestDto(getOrdersRequestDto);
        List<Order> orders = orderService.listOrders(getOrdersRequest);
        List<GetOrdersResponseDto> response = orderMapper.toGetOrdersResponseDto(orders);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
