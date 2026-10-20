package com.demo.first.app;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final Map<Integer, User> userDB = new HashMap<>();

    @PostMapping
    public String createUser(@RequestBody User user){
        System.out.println("Trying to create new user with email ::-> "+user.getEmail());
        userDB.putIfAbsent(user.getId(), user);
        return "User has been created";
    }

    @PutMapping
    public String updateUser(@RequestBody User user){
        System.out.println("Trying to update existing user with email ::-> " + user.getEmail());
        if(userDB.containsKey(user.getId())){
            // update
            userDB.put(user.getId(), user);
            return "Updated user with id " + user.getId();
        } else {
            // error
            return "Unable to find user with id " + user.getId();
        }
    }

    @GetMapping
    public Map<Integer, User> getUsers(){
        System.out.println("Trying to retrieve users ::-> ");
        return userDB;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId){
        System.out.println("Trying to delete with id ::-> " + userId);

        if(userDB.containsKey(userId)){
            final User user = userDB.get(userId);

            // delete
            userDB.remove(userId);
            return "User has been deleted from database " + user.getId();
        } else {
            // error
            return "Unable to delete user with id as its not exists " + userId;
        }
    }


}
