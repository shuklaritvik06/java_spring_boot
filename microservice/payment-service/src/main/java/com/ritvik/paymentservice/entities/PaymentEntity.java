package com.ritvik.paymentservice.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class PaymentEntity {
    @Id
    private ObjectId id;
    private String paymentId;
    private String payer;
    private String payedTo;
    private Integer price;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayedTo() {
        return payedTo;
    }

    public void setPayedTo(String payedTo) {
        this.payedTo = payedTo;
    }
}
