package com.example.spring_boot_crud.services.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.spring_boot_crud.config.RabbitMQConfig;
import com.example.spring_boot_crud.services.UserNotificationConsumer;

@Service
public class UserNotificationConsumerImpl implements UserNotificationConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    @Override
    public void receiveNotification(String message) {
        System.out.println("Received Notification: " + message);
    }
}