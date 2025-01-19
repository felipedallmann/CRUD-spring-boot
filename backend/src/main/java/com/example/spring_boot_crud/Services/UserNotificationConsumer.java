package com.example.spring_boot_crud.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.spring_boot_crud.config.RabbitMQConfig;

@Service
public class UserNotificationConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveNotification(String message) {
        System.out.println("Received Notification: " + message);
    }
}