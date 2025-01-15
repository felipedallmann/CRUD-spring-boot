package com.example.spring_boot_crud.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_boot_crud.dtos.UserCreateDTO;
import com.example.spring_boot_crud.dtos.UserResponseDTO;
import com.example.spring_boot_crud.entities.User;
import com.example.spring_boot_crud.exceptions.UserAlreadyExistsException;
import com.example.spring_boot_crud.exceptions.UserNotFoundException;
import com.example.spring_boot_crud.mappers.UserMapper;
import com.example.spring_boot_crud.repository.UserRepository;
import com.example.spring_boot_crud.services.UserService;

@Service

public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //create a user 
    public void createUser(UserCreateDTO userCreateDTO){
        User user = mapper.toEntity(userCreateDTO);
        Optional<User> existingUser = repository.findByEmail(user.getEmail());
        //if some user with the same email already exists, throws an exception
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException
                ("User with email: "+ user.getEmail() + " already exists.");
        }

        repository.save(user);
    }

    //read all the users
    public List<UserResponseDTO> getAllUsers(){
        List<User> users = repository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        return users.stream()
            .map(mapper::toResponseDTO).collect(Collectors.toList());

    }

    //read user searching by ID
    public UserResponseDTO getUserById(Long id){
        User user = repository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with ID: "+ id + " not found."));

        return mapper.toResponseDTO(user);
    }

    //read user searching by name
    public List<UserResponseDTO> getUserByName(String name){
        List<User> users = repository.findByNameContainingIgnoreCase(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with name: " + name);
        }
        return users.stream()
            .map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    //update a user by his id
    public void updateUser(Long id, UserCreateDTO updatedUserDTO){

        //validate if user with specified id exists
        User existingUser = repository.findById(id)   
            .orElseThrow(() -> new UserNotFoundException("User with ID: "+ id + " not found."));

        User updatedUser = mapper.toEntity(updatedUserDTO);
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
