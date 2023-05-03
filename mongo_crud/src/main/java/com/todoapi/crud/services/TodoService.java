package com.todoapi.crud.services;

import com.mongodb.client.result.UpdateResult;
import com.todoapi.crud.entity.Metadata;
import com.todoapi.crud.entity.TodoEntity;
import com.todoapi.crud.repository.TodoRepository;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TodoRepository todoRepository;
    public List<TodoEntity> getAllTodo(){
        return todoRepository.findAll();
    }
    public TodoEntity insertOneTodo(@org.jetbrains.annotations.NotNull @RequestBody TodoEntity todo){
        mongoTemplate.update(TodoEntity.class)
                .matching(Criteria.where("title").is(todo.getId()))
                .apply(new Update().push("meta").value(new Metadata(LocalDateTime.now(),LocalDateTime.now(),todo.getAuthor())))
                .first();
        return todoRepository.insert(new TodoEntity(todo.getTitle(),todo.getDescription(),todo.getDescription()));
    }
    public Optional<TodoEntity> deleteTodo(String id){
        Optional<TodoEntity> todo = todoRepository.findById(new ObjectId(id));
        todoRepository.deleteById(id);
        return todo;
    }
    public Optional<TodoEntity> updateTodo(String id, @NotNull @RequestBody TodoEntity todo){
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        Update update = new Update().set("title", todo.getTitle()).set("description", todo.getDescription()).set("author", todo.getAuthor());
        mongoTemplate.updateFirst(query,update,TodoEntity.class);
        return todoRepository.findById(new ObjectId(id));
    }
    public Optional<TodoEntity> getTodo(String id){
        Optional<TodoEntity> todo = todoRepository.findById(new ObjectId(id));
        return todo;
    }
}
