package com.ritvik.orderservice.client;

import com.ritvik.orderservice.records.PaymentEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface PaymentClient {
    @GetExchange("/payment/{id}")
    public PaymentEntity getPayment(@PathVariable String id);
}
