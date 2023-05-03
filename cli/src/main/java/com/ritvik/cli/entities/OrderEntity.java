package com.ritvik.cli.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    private ObjectId id;
    private String orderElement;
    private Integer price;
    private String buyer;

    public OrderEntity(String orderElement, Integer price, String buyer) {
        this.orderElement = orderElement;
        this.price = price;
        this.buyer = buyer;
    }
}
