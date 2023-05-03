package com.ritvik.relationships.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class AuthorEntity {
    @Id
    private Integer id;
    private String authorName;
    @ManyToOne(optional = false)
    @JoinColumn(name="book_id",nullable = false,referencedColumnName = "id")
    @JsonBackReference
    private BookEntity books;
}
