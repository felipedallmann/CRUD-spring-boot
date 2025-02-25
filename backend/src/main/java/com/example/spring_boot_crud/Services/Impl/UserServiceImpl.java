package com.example.spring_boot_crud.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.spring_boot_crud.dtos.UserCreateDTO;
import com.example.spring_boot_crud.dtos.UserResponseDTO;
import com.example.spring_boot_crud.entities.User;
import com.example.spring_boot_crud.exceptions.UserAlreadyExistsException;
import com.example.spring_boot_crud.exceptions.UserNotFoundException;
import com.example.spring_boot_crud.mappers.UserMapper;
import com.example.spring_boot_crud.repository.UserRepository;
import com.example.spring_boot_crud.services.UserNotificationProducer;
import com.example.spring_boot_crud.services.UserService;

@Service

public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserNotificationProducer notificationProducer;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, 
                            UserNotificationProducer notificationProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.notificationProducer = notificationProducer;
    }

    //create a user 
    @Override
    public void createUser(UserCreateDTO userCreateDTO){
        User user = mapper.toEntity(userCreateDTO);
        Optional<User> existingUser = repository.findByEmailAndDeletedFalse(user.getEmail());
        //if some user with the same email already exists, throws an exception
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException
                ("User with email: "+ user.getEmail() + " already exists.");
        }
        notificationProducer.sendNotification("User created: " + user.getName());
        repository.save(user);
    }

    //read all the users
    @Override
    public List<UserResponseDTO> getAllUsers(){
        List<User> users = repository.findAllByDeletedFalse();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        return users.stream()
            .map(mapper::toResponseDTO).collect(Collectors.toList());

    }

    //read user searching by ID
    @Override
    public UserResponseDTO getUserById(Long id){
        User user = repository.findByIdAndDeletedFalse(id)
        .orElseThrow(() -> new UserNotFoundException("User with ID: "+ id + " not found."));

        return mapper.toResponseDTO(user);
    }

    //read user searching by name
    @Override
    public List<UserResponseDTO> getUserByName(String name){
        List<User> users = repository.findByNameContainingIgnoreCaseAndDeletedFalse(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with name: " + name);
        }
        return users.stream()
            .map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    //update a user by his id
    @Override
    public void updateUser(Long id, UserCreateDTO updatedUserDTO){

        //validate if user with specified id exists
        User existingUser = repository.findByIdAndDeletedFalse(id)   
            .orElseThrow(() -> new UserNotFoundException("User with ID: "+ id + " not found."));

        // existingUser = mapper.toEntity(updatedUserDTO);
        repository.findByEmailAndDeletedFalse(updatedUserDTO.getEmail())
            .ifPresent(userWithSameEmail -> {
                if(userWithSameEmail.getId() != id){
                    throw new UserAlreadyExistsException("Email: " + updatedUserDTO.getEmail() + " already in use.");
                }
            });

        existingUser.setName(updatedUserDTO.getName());
        existingUser.setEmail(updatedUserDTO.getEmail());

        notificationProducer.sendNotification("User with id: " + existingUser.getId() + " updated");
        repository.save(existingUser);
    }
    //delete user by ID
    @Override
    public void deleteUser(Long id){
        User user = repository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new UserNotFoundException("User with ID: " +id + " not found."));
        // if(!repository.existsById(id)){
        //     throw new UserNotFoundException("User with ID: " +id + " not found.");
        // }
        user.setDeleted(true);
        repository.save(user);
        notificationProducer.sendNotification("User deleted with ID: " + id);
    }  

    @Override
    public List<UserResponseDTO> getDeletedUser(){
        List<User> deletedUsers = repository.findByDeletedTrue();
        if(deletedUsers.isEmpty()){
            throw new UserNotFoundException("No user deleted");
        }
        return deletedUsers.stream()
            .map(mapper::toResponseDTO).collect(Collectors.toList());
        }
}
