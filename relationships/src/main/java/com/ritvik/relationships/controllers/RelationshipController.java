package com.ritvik.relationships.controllers;

import com.ritvik.relationships.entities.AuthorEntity;
import com.ritvik.relationships.entities.BookEntity;
import com.ritvik.relationships.entities.CustomerEntity;
import com.ritvik.relationships.entities.IsbnEntity;
import com.ritvik.relationships.repositories.AuthorRepository;
import com.ritvik.relationships.repositories.BookRepository;
import com.ritvik.relationships.repositories.CustomerRepository;
import com.ritvik.relationships.repositories.IsbnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RelationshipController {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private IsbnRepository isbnRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorEntity>> authorEntities(){
        System.out.println(authorRepository.findAll());
        return  ResponseEntity.ok(null);
    }
    @GetMapping("/books")
    public ResponseEntity<List<BookEntity>> booksEntities(){
        return ResponseEntity.ok(bookRepository.findAll());
    }
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerEntity>> customerEntities(){
        return ResponseEntity.ok(customerRepository.findAll());
    }
    @GetMapping("/isbn")
    public ResponseEntity<List<IsbnEntity>> isbn(){
        return ResponseEntity.ok(isbnRepository.findAll());
    }
}
