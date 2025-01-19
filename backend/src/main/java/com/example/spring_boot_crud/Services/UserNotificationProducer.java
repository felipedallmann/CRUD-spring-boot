package com.example.spring_boot_crud.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.spring_boot_crud.config.RabbitMQConfig;


@Service
public class UserNotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserNotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(String message) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE_NAME,
            RabbitMQConfig.ROUTING_KEY,
            message
        );
    }
}
