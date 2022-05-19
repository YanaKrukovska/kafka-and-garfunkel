package com.krukovska.kafkaandgarfunkel.service.impl;

import com.krukovska.kafkaandgarfunkel.model.Order;
import com.krukovska.kafkaandgarfunkel.model.OrderStatus;
import com.krukovska.kafkaandgarfunkel.service.OrderService;
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
