package com.ritvik.graphql.graphql;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerInput {
    private String name;
    private String address;
    private Integer age;
}
