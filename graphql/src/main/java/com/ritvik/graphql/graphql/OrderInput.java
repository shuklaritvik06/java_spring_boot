package com.ritvik.graphql.graphql;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInput {
    private Integer amount;
    private String delivery_address;
    private Integer delivery_date;
    private Integer customer_id;
}
