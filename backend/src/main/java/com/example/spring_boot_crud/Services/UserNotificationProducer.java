package com.example.spring_boot_crud.services;

import org.springframework.stereotype.Service;


@Service
public interface UserNotificationProducer {
    public void sendNotification(String message);
}

