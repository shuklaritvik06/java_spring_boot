package com.todoapi.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {
    @Id
    private ObjectId id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String author;

    public Metadata(LocalDateTime created_at, LocalDateTime updated_at, String author) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.author = author;
    }
}
