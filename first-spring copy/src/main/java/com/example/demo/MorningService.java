package com.example.demo;

import org.springframework.stereotype.Component;

@Component("morningBean")
public class MorningService {

    public void SayGoodMorning(){
        System.out.println("Good Morning!!");
    }

}
