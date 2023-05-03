package com.ritvik.orderservice.records;

public record PaymentEntity(String paymentId, String payer, String payedTo, Integer price) {}
