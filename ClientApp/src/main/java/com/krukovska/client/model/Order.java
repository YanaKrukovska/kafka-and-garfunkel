package com.krukovska.client.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "client_name")
    private String clientName;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(String clientName, String description) {
        this.clientName = clientName;
        this.description = description;
    }

    public Order(String clientName, String description, OrderStatus status) {
        this.clientName = clientName;
        this.description = description;
        this.status = status;
    }
}
