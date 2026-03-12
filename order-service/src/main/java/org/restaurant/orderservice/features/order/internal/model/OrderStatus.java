package org.restaurant.orderservice.features.order.internal.model;

public enum OrderStatus {
    PENDING,        // order placed but not confirmed
    CONFIRMED,      // restaurant confirmed the order
    PREPARING,      // kitchen is preparing the food
    READY_FOR_PICKUP, // ready to be picked up by delivery or customer
    OUT_FOR_DELIVERY, // out with delivery person
    COMPLETED,      // delivered or picked up successfully
    CANCELLED
}
