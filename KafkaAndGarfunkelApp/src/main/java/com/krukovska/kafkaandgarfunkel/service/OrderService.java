package com.krukovska.kafkaandgarfunkel.service;

import com.krukovska.kafkaandgarfunkel.model.Order;
import com.krukovska.kafkaandgarfunkel.model.OrderStatus;

public interface OrderService {

    Order update(Order order, OrderStatus newStatus);

}
