package com.krukovska.palmetto.service;

import com.krukovska.palmetto.model.Order;
import com.krukovska.palmetto.model.OrderStatus;

public interface OrderService {

    Order update(Order order, OrderStatus newStatus);

}
