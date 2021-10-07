package com.krukovska.client.kafka.service;

import com.krukovska.client.model.Order;

public interface KafkaService {

    void sendOrder(String topic, Order order);

}
