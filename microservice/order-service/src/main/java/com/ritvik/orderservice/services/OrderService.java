package com.ritvik.orderservice.services;

import com.ritvik.orderservice.entities.OrderEntity;
import com.ritvik.orderservice.repositories.OrderRepository;
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
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    public OrderEntity insertOrder(@RequestBody OrderEntity orderEntity){
        LOGGER.info("Inserting new data in the database");
        return orderRepository.insert(orderEntity);
    }
    public Optional<OrderEntity> updateOrder(String id, @RequestBody OrderEntity orderEntity){
        LOGGER.info("Updating data in the database");
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        Update update = new Update().set("orderName", orderEntity.getOrderName()).set("buyer", orderEntity.getBuyer()).set("price", orderEntity.getPrice());
        mongoTemplate.updateFirst(query,update,OrderEntity.class);
        return orderRepository.findById(new ObjectId(id));
    }
    public Optional<OrderEntity> deleteOrder(String id){
        LOGGER.info("Deleting data in the database");
        Optional<OrderEntity> todo = orderRepository.findById(new ObjectId(id));
        orderRepository.deleteById(new ObjectId(id));
        return todo;

    }
    public List<OrderEntity> getAll(){
        LOGGER.info("Finding all data in the database");
        return orderRepository.findAll();
    }
    public Optional<OrderEntity> getOrder(String id){
        LOGGER.info("Finding a data in the database");
        return orderRepository.findById(new ObjectId(id));
    }
}
