package com.krukovska.kafkaandgarfunkel.kafka.listener;

import com.krukovska.kafkaandgarfunkel.kafka.service.KafkaService;
import com.krukovska.kafkaandgarfunkel.model.Order;
import com.krukovska.kafkaandgarfunkel.model.OrderStatus;
import com.krukovska.kafkaandgarfunkel.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.krukovska.kafkaandgarfunkel.model.OrderStatus.*;

@Slf4j
@Component
public class KafkaAndGarfunkelListeners {

    private final OrderService orderService;
    private final KafkaService kafkaService;

    @Autowired
    public KafkaAndGarfunkelListeners(OrderService orderService, KafkaService kafkaService) {
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
