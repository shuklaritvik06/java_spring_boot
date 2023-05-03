package com.todoapi.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;import org.springframework.data.mongodb.core.mapping.DocumentReference;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "todo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {
    @Id
    private ObjectId id;
    private String title;
    private String description;
    private String author;
    @DocumentReference
    private Metadata meta;

    public TodoEntity(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}
