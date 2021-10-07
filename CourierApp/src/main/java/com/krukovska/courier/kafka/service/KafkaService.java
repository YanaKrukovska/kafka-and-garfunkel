package com.krukovska.courier.kafka.service;

import com.krukovska.courier.model.Order;

public interface KafkaService {

    void sendOrder(String topic, Order order);

}
