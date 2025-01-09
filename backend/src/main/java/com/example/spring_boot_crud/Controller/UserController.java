package com.example.spring_boot_crud.Controller;

import java.lang.classfile.ClassFile.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_crud.Services.UserService;
import com.example.spring_boot_crud.Entities.User;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    //Create a user
    @PostMapping
    public void createUser(@RequestBody User user){
        service.createUser(user);
    }
    //Update a user
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        service.updateUser(id, updatedUser);
    }

    //Get all users 
    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    //Get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return service.getUserById(id);
    }

    //Get user by name
    @GetMapping(value = "/search", produces = "application/json")
    public List<User> getUserByName(@RequestParam String name) {
        return service.getUserByName(name);
    }

    //Delete a user 
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
    
    
}
