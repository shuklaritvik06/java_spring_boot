package com.ritvik.relationships.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class IsbnEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String isbnNo;
}
