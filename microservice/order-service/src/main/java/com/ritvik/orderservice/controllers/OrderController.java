package com.ritvik.orderservice.controllers;

import com.ritvik.orderservice.client.PaymentClient;
import com.ritvik.orderservice.entities.OrderEntity;
import com.ritvik.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentClient paymentClient;
    @PostMapping("/insert")
    public ResponseEntity<OrderEntity> addOrder(@RequestBody OrderEntity orderEntity){
        return new ResponseEntity<>(orderService.insertOrder(orderEntity), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<OrderEntity>> updateOrder(@PathVariable String id,@RequestBody OrderEntity orderEntity){
        return new ResponseEntity<>(orderService.updateOrder(id, orderEntity), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<OrderEntity>> deleteOrder(@PathVariable String id){
        return new ResponseEntity<>(orderService.deleteOrder(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<OrderEntity>> getAll(){
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderEntity>> getOrder(@PathVariable String id){
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }
    @GetMapping("/with-payments")
    public List<OrderEntity> getOrders(){
        List<OrderEntity> orderEntities = orderService.getAll();
        orderEntities.forEach(orderEntity -> {
            orderEntity.setPayment(paymentClient.getPayment(orderEntity.getId().toString()));
        });
        return orderEntities;
    }
}
