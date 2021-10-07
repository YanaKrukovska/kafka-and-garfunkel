package com.krukovska.courier.service;

import com.krukovska.courier.model.Order;
import com.krukovska.courier.model.OrderStatus;

public interface OrderService {

    Order update(Order order, OrderStatus newStatus);

}
