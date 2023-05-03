package com.ritvik.orderservice.entities;

import com.ritvik.orderservice.records.PaymentEntity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


public class OrderEntity {
    @Id
    private ObjectId id;
    private String orderName;
    private String buyer;
    private Integer price;
    private PaymentEntity payment;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public ObjectId getId() {
        return id;
    }
}
