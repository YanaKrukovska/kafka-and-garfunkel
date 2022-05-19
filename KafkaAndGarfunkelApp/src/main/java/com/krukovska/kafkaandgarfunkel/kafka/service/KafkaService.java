package com.krukovska.kafkaandgarfunkel.kafka.service;

import com.krukovska.kafkaandgarfunkel.model.Order;

public interface KafkaService {

    void sendOrder(String topic, Order order);

}
