package com.ritvik.relationships.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CustomerEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String customerName;
    private String customerAddress;
    @ManyToMany(mappedBy = "customers")
    @JsonManagedReference
    private List<BookEntity> books;
}
