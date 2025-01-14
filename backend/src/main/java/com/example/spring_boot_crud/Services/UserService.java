package com.example.spring_boot_crud.Services;

import java.util.List;
import java.util.Optional;

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

    //create a user 
    public void createUser(User user){
        Optional<User> existingUser = repository.findByEmail(user.getEmail());
        //if some user with the same email already exists, throws an exception
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException
                ("User with email: "+ user.getEmail() + " already exists.");
        }

        repository.save(user);
    }

    //read all the users
    public List<User> getAllUsers(){
        List<User> users = repository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        return users;

    }

    //read user searching by ID
    public User getUserById(Long id){
        return repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User with ID: "+ id + " not found."));
    }

    //read user searching by name
    public List<User> getUserByName(String name){
        List<User> users = repository.findByNameContainingIgnoreCase(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with name: " + name);
        }
        return users;
    }

    //update a user by his id
    public void updateUser(Long id, User updatedUser){
        //validate if user with specified id exists
        User existingUser = repository.findById(id)   
            .orElseThrow(() -> new UserNotFoundException("User with ID: "+ id + " not found."));

        repository.findByEmail(updatedUser.getEmail())
            .ifPresent(userWithSameEmail -> {
                if(userWithSameEmail.getId() != id){
                    throw new UserAlreadyExistsException("Email: " + updatedUser.getEmail() + " already in use.");
                }
            });

        updatedUser.setId(existingUser.getId());
        repository.save(updatedUser);
    }
    //delete user by ID
    public void deleteUser(Long id){
        if(!repository.existsById(id)){
            throw new UserNotFoundException("User with ID: " +id + " not found.");
        }
        repository.deleteById(id);
    }  
}
