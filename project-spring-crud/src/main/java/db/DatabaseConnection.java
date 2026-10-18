package db;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseConnection {
    private List<String> users;

    public void init(){
        users = new ArrayList<>();
        System.out.println("DB Connected");
    }

    public List<String> getUsers(){
        return users;
    }

    public String addUser(String user){
        users.add(user);
        return user;
    }

    public void cleanup(){
        System.out.println("DB Disconnected");
    }

}
