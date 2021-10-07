package com.krukovska.client.kafka.listener;

import com.krukovska.client.model.Order;
import com.krukovska.client.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientListeners {

    private final OrderService orderService;

    @Autowired
    public ClientListeners(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "notifications_topic", concurrency = "3")
    public void orderListener(ConsumerRecord<Long, Order> record) {
        log.info("Client listener received order {}", record.value());
        orderService.update(record.value());
    }
}
