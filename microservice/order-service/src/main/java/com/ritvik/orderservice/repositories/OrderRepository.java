package com.ritvik.orderservice.repositories;

import com.ritvik.orderservice.entities.OrderEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, ObjectId> {}
