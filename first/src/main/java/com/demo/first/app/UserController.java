package com.demo.first.app;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final Map<Integer, User> userDB = new HashMap<>();

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        if(userDB.containsKey(user.getId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists in database");
        }else{
            System.out.println("Trying to create new user with email ::-> "+user.getEmail());
            userDB.putIfAbsent(user.getId(), user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User has been created");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user){
        System.out.println("Trying to update existing user with email ::-> " + user.getEmail());
        if(userDB.containsKey(user.getId())){
            // update
            userDB.put(user.getId(), user);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Updated user in database");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to find user with id in database");
        }
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getUsers(){
        System.out.println("Trying to retrieve users ::-> ");
        return ResponseEntity.ok(userDB.values());
//        return ResponseEntity.status(HttpStatus.OK).body(userDB.values());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
        System.out.println("Trying to delete with id ::-> " + userId);

        if(userDB.containsKey(userId)){
            userDB.remove(userId);
            return ResponseEntity.ok("User has been deleted from database");
//            return ResponseEntity.status(HttpStatus.OK).body("User has been deleted from database");
        } else {
            // error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to delete user with id as its not exists");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        if(!userDB.containsKey(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userDB.get(id));
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
        System.out.println(name);

        List<User> users = userDB.values().stream().filter(u -> u.getName().contains(name)).filter(u -> u.getEmail().contains(email)).toList();

        return ResponseEntity.status(200).body(users);
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
