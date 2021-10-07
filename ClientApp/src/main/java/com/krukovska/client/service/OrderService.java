package com.krukovska.client.service;

import com.krukovska.client.model.Order;

public interface OrderService {

    Order getOrderById(long id);

    Order createOrder(Order order);

    Order update(Order order);

}
