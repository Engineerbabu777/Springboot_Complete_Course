package com.example.loose;

public class UserService implements NotificationService {

    NotificationService notificationService;

    public UserService(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @Override
    public void send(String message){
        // send message
        notificationService.send("hello");
    }
}
