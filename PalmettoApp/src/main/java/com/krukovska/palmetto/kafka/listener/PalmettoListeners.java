package com.krukovska.palmetto.kafka.listener;

import com.krukovska.palmetto.kafka.service.KafkaService;
import com.krukovska.palmetto.model.Order;
import com.krukovska.palmetto.model.OrderStatus;
import com.krukovska.palmetto.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.krukovska.palmetto.model.OrderStatus.*;

@Slf4j
@Component
public class PalmettoListeners {

    private final OrderService orderService;
    private final KafkaService kafkaService;

    @Autowired
    public PalmettoListeners(OrderService orderService, KafkaService kafkaService) {
        this.orderService = orderService;
        this.kafkaService = kafkaService;
    }

    @KafkaListener(topics = "order_topic", concurrency = "3")
    public void orderListener(ConsumerRecord<Long, Order> record) {
        process(record);
    }

    private void process(ConsumerRecord<Long, Order> record) {
        log.info("Received order={}", record.value());
        updateStatus(record.value(), IN_PROGRESS);
        simulateWaiting();
        updateStatus(record.value(), READY);
    }

    private void updateStatus(Order order, OrderStatus orderStatus) {
        Order updatedOrder = orderService.update(order, orderStatus);
        kafkaService.sendOrder("notifications_topic", updatedOrder);
    }

    private void simulateWaiting() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
