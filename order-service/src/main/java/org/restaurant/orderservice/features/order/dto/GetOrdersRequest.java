package org.restaurant.orderservice.features.order.dto;

public class GetOrdersRequest {

    String email;
    Boolean confirmed;


    public GetOrdersRequest() {
    }

    public GetOrdersRequest(String email, Boolean confirmed) {
        this.email = email;
        this.confirmed = confirmed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

}
