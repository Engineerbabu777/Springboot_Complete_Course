package com.demo.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayhello(){
        return "helo";
    }


//    @GetMapping("/user")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(){
        return new User(1, "Jhon", "jhon@email.com");
    }

}
