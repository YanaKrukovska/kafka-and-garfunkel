package com.krukovska.client.controller;

import com.krukovska.client.kafka.service.KafkaService;
import com.krukovska.client.model.Order;
import com.krukovska.client.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final KafkaService kafkaService;

    @Autowired
    public OrderController(OrderService orderService, KafkaService kafkaService) {
        this.orderService = orderService;
        this.kafkaService = kafkaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getStatus(@PathVariable long id) {
        log.info("Calling get order endpoint, id={}", id);
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        log.info("Calling post order endpoint, order={}", order);
        Order newOrder = orderService.createOrder(order);
        log.info("Created new order {}", newOrder);
        kafkaService.sendOrder("order_topic", newOrder);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }
}
