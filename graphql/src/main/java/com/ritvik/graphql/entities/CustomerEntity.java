package com.ritvik.graphql.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "customerEntity")
public class CustomerEntity {
    @Id
    private ObjectId id;
    private String address;
    private String name;
    private Integer age;
}
