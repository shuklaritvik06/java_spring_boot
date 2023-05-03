package com.ritvik.cli.services;

import com.ritvik.cli.entities.OrderEntity;
import com.ritvik.cli.repositories.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Optional<OrderEntity> findOrderById(String Id){
        return orderRepository.findById(new ObjectId(Id));
    }
    public Optional<OrderEntity> deleteOrderById(String Id){
        Optional<OrderEntity> element = orderRepository.findById(new ObjectId(Id));
        orderRepository.deleteById(new ObjectId(Id));
        return element;
    }
    public List<OrderEntity> getAllOrders(){
        return orderRepository.findAll();
    }
    public Optional<OrderEntity> updateOrderById(String id, String buyer, String element, Integer price){
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        Update update = new Update().set("buyer", buyer).set("orderElement", element).set("price", price);
        mongoTemplate.updateFirst(query,update,OrderEntity.class);
        return orderRepository.findById(new ObjectId(id));
    }

    public OrderEntity insertOrder(String buyer, String element, Integer price){
        return orderRepository.insert(new OrderEntity(element,price,buyer));
    }
}