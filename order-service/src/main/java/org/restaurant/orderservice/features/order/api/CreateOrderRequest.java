package org.restaurant.orderservice.features.order.api;

import org.restaurant.orderservice.features.order.internal.model.OrderItemRequest;
import org.restaurant.orderservice.features.order.internal.model.PaymentType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CreateOrderRequest {

    private UUID id;
    private String userName;
    private List<OrderItemRequest> items;
    private LocalDateTime placedAt;
    private PaymentType paymentType;
    private String comment;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(UUID id, String userName, List<OrderItemRequest> items, LocalDateTime placedAt, PaymentType paymentType, String comment) {
        this.id = id;
        this.userName = userName;
        this.items = items;
        this.placedAt = placedAt;
        this.paymentType = paymentType;
        this.comment = comment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrderRequest that = (CreateOrderRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", items=" + items +
                ", placedAt=" + placedAt +
                ", paymentType=" + paymentType +
                ", comment='" + comment + '\'' +
                '}';
    }
}