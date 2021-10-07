package com.krukovska.client.service.impl;

import com.krukovska.client.model.Order;
import com.krukovska.client.repository.OrderRepository;
import com.krukovska.client.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order getOrderById(long id) {
        return repository.getOrderById(id);
    }

    @Override
    public Order createOrder(Order order) {
        log.info("Create new order={}", order);
        return repository.save(order);
    }

    @Override
    public Order update(Order order) {
        log.info("Update order id={}", order.getId());
        return repository.save(order);
    }

}
