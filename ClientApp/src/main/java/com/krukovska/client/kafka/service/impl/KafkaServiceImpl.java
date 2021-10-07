package com.krukovska.client.kafka.service.impl;

import com.krukovska.client.kafka.service.KafkaService;
import com.krukovska.client.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<Long, Order> kafkaTemplate;

    @Autowired
    public KafkaServiceImpl(KafkaTemplate<Long, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(String topic, Order order) {
        ListenableFuture<SendResult<Long, Order>> future = kafkaTemplate.send(topic, order);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<Long, Order> result) {
                RecordMetadata recordMetadata = result.getRecordMetadata();
                log.info("Sent order {} with offset {} to topic {} partition {}", order, recordMetadata.offset(),
                        recordMetadata.topic(), recordMetadata.partition());
            }

            @Override
            public void onFailure(Throwable e) {
                log.info("Unable to send order {} due to {}", order, e.getMessage());
            }
        });
    }
}
