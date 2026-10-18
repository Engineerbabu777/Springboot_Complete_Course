package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(String name){
        userRepository.adduser(name);
    }

    public List<String> getAllUsers(){
        return userRepository.findAll();
    }


}
