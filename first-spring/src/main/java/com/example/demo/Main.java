package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationBeanContext.xml");

        GreetingService greetingService = (GreetingService) context.getBean("greetingBean");
        MorningService morningService = (MorningService) context.getBean("morningBean");

        greetingService.sayHello();
        morningService.SayGoodMorning();

    }
}
