package com.todoapi.crud.repository;

import com.todoapi.crud.entity.TodoEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends MongoRepository<TodoEntity, ObjectId> {
    @Query(value="{'_id' : ?0}", delete = true)
    TodoEntity deleteById(String id);
    @Query(value="{'_id' : ?0}")
    TodoEntity updateById(String id);
}
