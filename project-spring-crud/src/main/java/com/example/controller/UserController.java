package com.example.controller;

import com.example.service.UserService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    private UserController(UserService userService){this.userService = userService;}

    public void createUser(String name){
        userService.addUser(name);
        System.out.println("User Added:- "+ name);
    }


    public void listUsers(){
        List<String> users = userService.getAllUsers();
        System.out.println("Users List:- "+ users);
        for(String user: users){
            System.out.println("User Name:- "+ user);
        }
    }
}
