package com.krukovska.courier.service.impl;

import com.krukovska.courier.model.Order;
import com.krukovska.courier.model.OrderStatus;
import com.krukovska.courier.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order update(Order order, OrderStatus newStatus) {
        log.info("Update order id={}, set newStatus={}", order.getId(), newStatus);
        order.setStatus(newStatus);
        return order;
    }

}
