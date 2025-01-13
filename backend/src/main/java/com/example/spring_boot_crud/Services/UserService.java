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
        return repository.findAll();
    }

    //read user searching by ID
    public User getUserById(Long id){
        Optional <User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }else{
            throw new UserNotFoundException("User with ID: "+ id + " not found.");
        }
    }

    //read user searching by name
    public List<User> getUserByName(String name){
        List<User> users = repository.findByNameContainingIgnoreCase(name);
        if (!users.isEmpty()) {
            return users;
        }else{
            throw new UserNotFoundException("No user found with name: " + name);
        }
    }

    //update a user by his id
    public void updateUser(Long id, User updatedUser){
        Optional<User> existingUser = repository.findById(id);
        //validate if user with specified id exists
        if(existingUser.isEmpty()) {
            throw new UserNotFoundException("User with ID: "+ id + " not found.");
        }
        
        //Validates if the user modification violates the email constraint.
        //If the email to be updated already exists, an exception will be thrown.
        Optional <User> userWithSameEmail = repository.findByEmail(updatedUser.getEmail());
        if(userWithSameEmail.isPresent() && userWithSameEmail.get().getId() != id){
            throw new UserAlreadyExistsException("Email: "+ updatedUser.getEmail() + " already in use");
        }
        updatedUser.setId(id);
        repository.save(updatedUser);
    }
    //delete user by ID
    public void deleteUser(Long id){
        Optional<User> existingUser = repository.findById(id);

        //Checks if a user with the specified ID exists
        //If not, an exception will be thrown
        if (existingUser.isEmpty()){
            throw new UserNotFoundException("User with ID: " +id + " not found.");
        }
        repository.deleteById(id);
    }  
}
