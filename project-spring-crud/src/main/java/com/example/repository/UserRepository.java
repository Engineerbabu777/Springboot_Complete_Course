package com.example.repository;

import com.example.db.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final DatabaseConnection db;

    public UserRepository(DatabaseConnection db){
        this.db = db;
    }

    public List<String> findAll(){
        return db.getUsers();
    }

    public List<String> adduser(String user){
        db.add(user);
        return findAll();
    }
}
