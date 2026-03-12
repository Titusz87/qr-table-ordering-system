package org.restaurant.orderservice.features.order.internal.model;

import jakarta.persistence.*;
import org.restaurant.orderservice.features.user.internal.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "order_data")
public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        @Column(name = "placed_at")
        private LocalDateTime placedAt;
        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
        private List<OrderItem> items;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;
        @Enumerated(EnumType.STRING)
        private OrderStatus status;
        @Enumerated(EnumType.STRING)
        @Column(name = "payment_type")
        private PaymentType paymentType;
        private String comment;
        @Column(name="total", precision=10, scale=2)
        private BigDecimal total;

        public Order() {
        }

        public Order(UUID id, LocalDateTime placedAt, List<OrderItem> items, User user, OrderStatus status, PaymentType paymentType, String comment) {
                this.id = id;
                this.placedAt = placedAt;
                this.items = items;
                this.user = user;
                this.status = status;
                this.paymentType = paymentType;
                this.comment = comment;
        }
        public BigDecimal calculateTotal(){
                if (items == null || items.isEmpty()) {
                        return BigDecimal.ZERO;
                }
                return items.stream()
                        .map(item -> item.getDish().getPrice()
                                .multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal.ZERO,BigDecimal::add);

        }

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public LocalDateTime getPlacedAt() {
                return placedAt;
        }

        public void setPlacedAt(LocalDateTime placedAt) {
                this.placedAt = placedAt;
        }

        public List<OrderItem> getItems() {
                return items;
        }

        public void setItems(List<OrderItem> items) {
                this.items = items;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }
        public String getUserName() {
                return user.getUserName();
        }

        public OrderStatus getStatus() {
                return status;
        }

        public void setStatus(OrderStatus status) {
                this.status = status;
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

        public BigDecimal getTotal() {
                return total;
        }

        public void setTotal(BigDecimal total) {
                this.total = total;
        }

        public boolean equals(Object object) {
                if (object == null || getClass() != object.getClass()) return false;
                if (!super.equals(object)) return false;
                Order order = (Order) object;
                return Objects.equals(id, order.id);
        }

        public int hashCode() {
                return Objects.hash(super.hashCode(), id);
        }

        @Override
        public String toString() {
                return "Order{" +
                        "id=" + id +
                        ", placedAt=" + placedAt +
                        ", items=" + items +
                        ", user=" + user +
                        ", status=" + status +
                        ", paymentType=" + paymentType +
                        ", comment='" + comment + '\'' +
                        '}';
        }
}
