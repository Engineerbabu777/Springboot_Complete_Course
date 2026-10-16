package com.example.demo;

import org.springframework.stereotype.Component;

@Component("greetingBean")
public class GreetingService {

    public void sayHello(){
        System.out.println("hello from Spring!");
    }
}
