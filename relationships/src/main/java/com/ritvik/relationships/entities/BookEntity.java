package com.ritvik.relationships.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class BookEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String bookName;
    @OneToOne
    @JoinColumn(name = "isbn_id", referencedColumnName = "id")
    private IsbnEntity isbn;
    @OneToMany(mappedBy = "books",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AuthorEntity> authors;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "books_customer",
            joinColumns = @JoinColumn(name="id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="customer_id", referencedColumnName="id"))
    @JsonBackReference
    private List<CustomerEntity> customers;
}
