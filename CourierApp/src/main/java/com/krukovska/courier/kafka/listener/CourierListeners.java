package com.krukovska.courier.kafka.listener;

import com.krukovska.courier.kafka.service.KafkaService;
import com.krukovska.courier.model.Order;
import com.krukovska.courier.model.OrderStatus;
import com.krukovska.courier.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.krukovska.courier.model.OrderStatus.*;

@Slf4j
@Component
public class CourierListeners {

    private final OrderService orderService;
    private final KafkaService kafkaService;

    @Autowired
    public CourierListeners(OrderService orderService, KafkaService kafkaService) {
        this.orderService = orderService;
        this.kafkaService = kafkaService;
    }

    @KafkaListener(topics = "notifications_topic", concurrency = "3")
    public void notificationListener(ConsumerRecord<Long, Order> record) {
        log.info("Received order={}", record.value());

        Order order = record.value();

        if (order.getStatus() == READY) {
            log.info("Order id={} is ready", order.getId());
            updateStatus(order, ON_THE_WAY);
            simulateWaiting();
            updateStatus(order, DELIVERED);
        }
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
