package com.demo.first.app.service;

import com.demo.first.app.exceptions.UserNotFoundException;
import com.demo.first.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Map<Integer, User> userDB = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public boolean createUser(User user){
        if(userDB.containsKey(user.getId())){
            return false;
        }else{
            logger.info("Trying to create new user with email ::-> "+user.getEmail() + "...INFO");
            logger.debug("Trying to create new user with email ::-> "+user.getEmail() + "...DEBUG");
            logger.trace("Trying to create new user with email ::-> "+user.getEmail() + "...TRACE");
            userDB.putIfAbsent(user.getId(), user);
            return true;
        }
    }


    public boolean updateUser(User user){
        System.out.println("Trying to update existing user with email ::-> " + user.getEmail());
        if(userDB.containsKey(user.getId())){
            // update
            userDB.put(user.getId(), user);
            return true;
        } else {
            throw new UserNotFoundException("User with this ID "+user.getId() + " not exists");
        }
    }

    public Collection<User> getUsers(){
        System.out.println("Trying to retrieve users ::-> ");
        return userDB.values();
    }

    public boolean deleteUser(int userId){
        System.out.println("Trying to delete with id ::-> " + userId);

        if(userDB.containsKey(userId)){
            userDB.remove(userId);
            return true;
        } else {
            throw new UserNotFoundException("User with this ID "+userId + " not exists");
        }
    }

    public User getUser(@PathVariable Integer id){
        return userDB.get(id);
    }


    public List<User> searchUsers(
            String name,
            String email
    ){
        System.out.println(name);

        return userDB.values().stream().filter(u -> u.getName().contains(name)).filter(u -> u.getEmail().contains(email)).toList();
    }
}
