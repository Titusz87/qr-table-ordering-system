package org.restaurant.orderservice.features.order.internal.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "payment_data")
public class Payment {
    @Id
    private UUID id;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private Integer amount;
    private String reference;

    public Payment() {
    }

    public Payment(UUID id, PaymentType paymentType, Integer amount, String reference) {
        this.id = id;
        this.paymentType = paymentType;
        this.amount = amount;
        this.reference = reference;
    }

    public UUID getPayment_id() {
        return id;
    }

    public void setPayment_id(UUID id) {
        this.id = id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment_id=" + id +
                ", paymentType=" + paymentType +
                ", amount=" + amount +
                ", reference='" + reference + '\'' +
                '}';
    }
}
