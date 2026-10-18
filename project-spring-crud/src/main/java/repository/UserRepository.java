package repository;

import db.DatabaseConnection;
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
        System.out.println("Added new User in DB");
        return findAll();
    }
}
