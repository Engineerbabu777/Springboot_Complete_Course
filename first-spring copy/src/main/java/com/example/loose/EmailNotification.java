package com.example.loose;


import org.springframework.stereotype.Component;

@Component("EmailNotification")
public class EmailNotification implements NotificationService{

    @Override
    public void send(String message){
        System.out.println("Email Notification Service:: "+ message);
    }
}
