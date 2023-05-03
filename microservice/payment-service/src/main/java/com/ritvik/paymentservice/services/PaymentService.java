package com.ritvik.paymentservice.services;

import com.ritvik.paymentservice.entities.PaymentEntity;
import com.ritvik.paymentservice.repositories.PaymentRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);
    public PaymentEntity insertPayment(@RequestBody PaymentEntity paymentEntity){
        LOGGER.info("Inserting new data in the database");
        return paymentRepository.insert(paymentEntity);
    }
    public Optional<PaymentEntity> updatePayment(String id, @RequestBody PaymentEntity paymentEntity){
        LOGGER.info("Updating data in the database");
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        Update update = new Update().set("paymentId", paymentEntity.getPaymentId()).set("payer", paymentEntity.getPayer()).set("payedTo", paymentEntity.getPayedTo()).set("price", paymentEntity.getPrice());
        mongoTemplate.updateFirst(query,update,PaymentEntity.class);
        return paymentRepository.findById(new ObjectId(id));
    }
    public Optional<PaymentEntity> deletePayment(String id){
        LOGGER.info("Deleting data in the database");
        Optional<PaymentEntity> todo = paymentRepository.findById(new ObjectId(id));
        paymentRepository.deleteById(new ObjectId(id));
        return todo;

    }
    public List<PaymentEntity> getAll(){
        LOGGER.info("Finding all data in the database");
        return paymentRepository.findAll();
    }
    public Optional<PaymentEntity> getPayment(String id){
        LOGGER.info("Finding a data in the database");
        return paymentRepository.findById(new ObjectId(id));
    }
}
