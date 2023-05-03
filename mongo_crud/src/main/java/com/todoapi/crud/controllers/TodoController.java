package com.todoapi.crud.controllers;

import com.todoapi.crud.entity.TodoEntity;
import com.todoapi.crud.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TodoController {
    @Autowired
    private TodoService service;
    @GetMapping("/")
    public String getHello(){
        return "Welcome to TODO Api. API is currently up and working!";
    }
    @GetMapping("/todo/{id}")
    public ResponseEntity<Optional<TodoEntity>> getTodo(@PathVariable String id){
        return new ResponseEntity<Optional<TodoEntity>>(service.getTodo(id),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<TodoEntity>> getTodos() {
        return new ResponseEntity<List<TodoEntity>>(service.getAllTodo(), HttpStatus.OK);
    }
    @PostMapping("/insert")
    public ResponseEntity<TodoEntity> insertTodos(@RequestBody TodoEntity todo){
        return new ResponseEntity<TodoEntity>(service.insertOneTodo(todo),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<TodoEntity>> deleteTodo(@PathVariable String id){
        return new ResponseEntity<Optional<TodoEntity>>(service.deleteTodo(id),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<TodoEntity>> updateTodo(@PathVariable String id, @RequestBody TodoEntity todo){
        return new ResponseEntity<Optional<TodoEntity>>(service.updateTodo(id, todo),HttpStatus.OK);
    }
}
