package com.demo.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayhello(){
        return "helo";
    }


    @GetMapping("/user")
    public User getUser(){
        return new User(1, "Jhon", "jhon@email.com");
    }

}
