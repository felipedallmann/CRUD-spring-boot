package com.example.spring_boot_crud.Repository;
import com.example.spring_boot_crud.Entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final Map<Long, User> users = new HashMap<>(); 
    private long currentId = 1;

     // Create: Saves a new user with an automatically assigned ID
    public void saveUser(User user){
        user.setId(currentId);
        users.put(currentId, user);
        currentId++;
    }

    //read all users
    public Map<Long, User> findAll(){
        return new HashMap<>(users);
    }

    //find users by their id
    public Optional<User> findById(Long id){
        return Optional.ofNullable(users.get(id));
    }

    //find user by their names
    public List<User> findByName(String name){
        return users.values().stream()
            .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
            .toList();
    }

    //find user by their email
    public Optional<User> findByEmail(String email){
        return users.values().stream()
        .filter(user -> user.getEmail().equals(email)).findFirst();
    }

    //Updates an existing user by their ID
    public void updateUser(Long id, User user){
        users.put(id, user);
    }

    //Removes a user by their ID
    public void deleteById(Long id){
        users.remove(id);
    }
}
