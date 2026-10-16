package com.example.demo;

import com.example.loose.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        GreetingService greetingService = (GreetingService) context.getBean("greetingBean");
//        MorningService morningService = (MorningService) context.getBean("morningBean");
        MorningService morningService = context.getBean(MorningService.class);

        greetingService.sayHello();
        morningService.SayGoodMorning();

        UserService userService = (UserService) context.getBean("UserServiceSMS");
        userService.send("Send notification via email");

    }
}
