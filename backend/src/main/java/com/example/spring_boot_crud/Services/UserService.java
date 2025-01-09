package com.example.spring_boot_crud.Services;

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_boot_crud.Entities.User;
import com.example.spring_boot_crud.Exceptions.UserAlreadyExistsException;
import com.example.spring_boot_crud.Exceptions.UserNotFoundException;
import com.example.spring_boot_crud.Repository.UserRepository;


@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    //create
    public void createUser(User user){
        Optional<User> existingUser = repository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException
                ("User with email: "+ user.getEmail() + " already exists.");
        }

        repository.saveUser(user);
    }

    //read
    public List<User> getAllUsers(){
        return repository.findAll().values().stream().toList();
    }

    public User getUserById(Long id){
        Optional <User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }else{
            throw new UserNotFoundException("User with ID: "+ id + " not found.");
        }
    }

    public List<User> getUserByName(String name){
        List<User> users = repository.findByName(name);
        if (!users.isEmpty()) {
            return users;
        }else{
            throw new UserNotFoundException("No user found with name: " + name);
        }
    }

    //update
    public void updateUser(Long id, User updatedUser){
        Optional<User> existingUser = repository.findById(id);

        if(existingUser.isEmpty()) {
            throw new UserNotFoundException("User with ID: "+ id + " not found.");
        }
        
        Optional <User> userWithSameEmail = repository.findByEmail(updatedUser.getEmail());
        if(userWithSameEmail.isPresent() && userWithSameEmail.get().getId() != id){
            throw new UserAlreadyExistsException("Email: "+ id + " already in use");
        }
        updatedUser.setId(id);
        repository.updateUser(id, updatedUser);
    }
    //delete
    public void deleteUser(Long id){
        Optional<User> existingUser = repository.findById(id);

        if (existingUser.isPresent()) {
            repository.deleteById(id);
        }else{
            throw new UserNotFoundException("User with ID: " +id + " not found.");
        }
        repository.deleteById(id);
    }  
}
