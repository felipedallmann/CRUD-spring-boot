package com.example.spring_boot_crud.services;

import org.springframework.stereotype.Service;

@Service
public interface UserNotificationConsumer {
    public void receiveNotification(String message);
}