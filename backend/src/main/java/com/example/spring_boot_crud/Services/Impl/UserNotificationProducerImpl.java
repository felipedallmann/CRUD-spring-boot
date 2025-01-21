package com.example.spring_boot_crud.services.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.spring_boot_crud.config.RabbitMQConfig;
import com.example.spring_boot_crud.services.UserNotificationProducer;


@Service
public class UserNotificationProducerImpl implements UserNotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserNotificationProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendNotification(String message) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE_NAME,
            RabbitMQConfig.ROUTING_KEY,
            message
        );
    }
}
