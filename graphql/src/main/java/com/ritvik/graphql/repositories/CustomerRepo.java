package com.ritvik.graphql.repositories;

import com.ritvik.graphql.entities.CustomerEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends MongoRepository<CustomerEntity, ObjectId> {}