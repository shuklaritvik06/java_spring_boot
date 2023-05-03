package com.ritvik.graphql.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orderEntity")
public class GraphqlEntity {
    @Id
    private ObjectId id;
    private Integer amount;
    private String delivery_address;
    private Integer delivery_date;
    private Integer customer_id;
}
