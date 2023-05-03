package com.ritvik.paymentservice.controllers;

import com.ritvik.paymentservice.entities.PaymentEntity;
import com.ritvik.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/insert")
    public ResponseEntity<PaymentEntity> addPayment(@RequestBody PaymentEntity paymentEntity){
        return new ResponseEntity<>(paymentService.insertPayment(paymentEntity), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<PaymentEntity>> updatePayment(@PathVariable String id, @RequestBody PaymentEntity paymentEntity){
        return new ResponseEntity<>(paymentService.updatePayment(id, paymentEntity), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<PaymentEntity>> deletePayment(@PathVariable String id){
        return new ResponseEntity<>(paymentService.deletePayment(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PaymentEntity>> getAll(){
        return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<PaymentEntity>> getPayment(@PathVariable String id){
        return new ResponseEntity<>(paymentService.getPayment(id), HttpStatus.OK);
    }
}
