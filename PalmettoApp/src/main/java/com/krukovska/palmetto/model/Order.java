package com.krukovska.palmetto.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private long id;
    private String clientName;
    private String description;
    private OrderStatus status;

}
