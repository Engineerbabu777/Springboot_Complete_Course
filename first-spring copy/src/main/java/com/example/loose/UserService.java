package com.example.loose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("UserServiceSMS")
public class UserService implements NotificationService {

    NotificationService notificationService;

////    @Autowired
//    public UserService(@Qualifier("EmailNotification") NotificationService notificationService){
//        this.notificationService = notificationService;
//    }

//    @Autowired
public UserService(NotificationService notificationService){
    this.notificationService = notificationService;
}

    @Override
    public void send(String message){
        // send message
        notificationService.send("hello");
    }
}
