package com.demo.first.app.controller;


import com.demo.first.app.service.UserService;
import com.demo.first.app.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final Map<Integer, User> userDB = new HashMap<>();

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        final boolean response = this.userService.createUser(user);
        if(response){
            return ResponseEntity.status(HttpStatus.CREATED).body("User has been created");
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists in database");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user){
        final boolean response = this.userService.updateUser(user);
           if(response){
               return ResponseEntity.status(HttpStatus.CREATED).body("Updated user in database");
           }
               return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to find user with id in database");
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
//        return ResponseEntity.status(HttpStatus.OK).body(userDB.values());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
        final boolean response = this.userService.deleteUser(userId);

        if(response){
            return ResponseEntity.ok("User has been deleted from database");
//            return ResponseEntity.status(HttpStatus.OK).body("User has been deleted from database");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to delete user with id as its not exists");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){

        final User response = this.userService.getUser(id);
        if(response == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping("{id}/order/{orderId}/sample/{sampleId}")
    public ResponseEntity<?> getUser(@PathVariable Integer id, @PathVariable String orderId, @PathVariable String sampleId){
        if(!userDB.containsKey(id)){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OrderId ==> "+orderId + " ------ SampleId ==> " + sampleId);
        }
        return ResponseEntity.ok(userDB.get(id));
    }

//    @GetMapping({"/users", "{id}/order/{orderId}/sample/{sampleId}"})
//    public ResponseEntity<?> getUser(@PathVariable Integer id, @PathVariable String orderId, @PathVariable String sampleId){
//        if(!userDB.containsKey(id)){
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OrderId ==> "+orderId + " ------ SampleId ==> " + sampleId);
//        }
//        return ResponseEntity.ok(userDB.get(id));
//    }


    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = false, defaultValue = "aw") String name,
            @RequestParam(required = false, defaultValue = "aw") String email
    ){
        return ResponseEntity.status(200).body(userService.searchUsers(name, email));
    }

    @GetMapping("info")
    public String getInfo(@RequestHeader String userAgent){
        return "User Agent : ==> "+userAgent;
    }

    @GetMapping("combined-api/{name}")
    public String combinedApi(@RequestHeader String userAgent, @PathVariable String name, @RequestParam String lang, @RequestBody User user){
        return "User Agent : ==> "+userAgent + " < ---- > User Name : ==> "+name + " < ---- > User Lang : ==> "+lang + " < ---- > User email : ==> "+user.getEmail();
    }





}
