package com.krukovska.palmetto.kafka.service;

import com.krukovska.palmetto.model.Order;

public interface KafkaService {

    void sendOrder(String topic, Order order);

}
