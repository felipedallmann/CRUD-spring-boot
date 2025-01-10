package com.example.spring_boot_crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Endpoints para gerenciamento de usuários")
public class UserController {

    private final UserService service;
    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    //Create a user POST/users
    @Operation(summary = "Criar um novo usuário")
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody User user){
        service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created succesfully");
    }

    //Update a user PUT/[id]
    @Operation(summary = "Atualizar um usuário")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@Valid @PathVariable Long id, @RequestBody User updatedUser) {
        service.updateUser(id, updatedUser);
        return ResponseEntity.ok("User updated successfully");
    }

    //Get all users GET/users
    @Operation(summary = "Buscar todos os usuários")
    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    //Get user by id GET/users/[id]
    @Operation(summary = "Buscar usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //Get user by name GET/users/search?name=[name]
    @Operation(summary = "Buscar usuário por nome")
    @GetMapping(value = "/search", produces = "application/json")
    public List<User> getUserByName(@RequestParam String name) {
        return service.getUserByName(name);
    }

    //Delete a user DELETE/users/[id]
    @Operation(summary = "Deletar usuário por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
    
    
}
