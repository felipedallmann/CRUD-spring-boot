package com.example.spring_boot_crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_crud.dtos.UserCreateDTO;
import com.example.spring_boot_crud.dtos.UserResponseDTO;
import com.example.spring_boot_crud.response.ApiResponse;
import com.example.spring_boot_crud.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Endpoints to user management")
@Validated  // Adicione esta anotação
public class UserController {

    private final UserService service;
    
    public UserController(UserService service){
        this.service = service;
    }

    //Create a user POST/users
    @Operation(summary = "Criar um novo usuário")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createUser(@Valid @RequestBody UserCreateDTO user){
        service.createUser(user);
        ApiResponse<String> response = ApiResponse.success("User created successfully", HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Update a user PUT/[id]
    @Operation(summary = "Atualizar um usuário")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@Valid @PathVariable Long id, @RequestBody UserCreateDTO updatedUser) {
        service.updateUser(id, updatedUser);
        ApiResponse<String> response = ApiResponse.success("User updates successfully", HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Get all users GET/users
    @Operation(summary = "Buscar todos os usuários")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers(){
        List<UserResponseDTO> users = service.getAllUsers();
        ApiResponse<List<UserResponseDTO>> response = ApiResponse.success(users, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Get user by id GET/users/[id]
    @Operation(summary = "Buscar usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@PathVariable Long id){
        UserResponseDTO user = service.getUserById(id);
        ApiResponse<UserResponseDTO> response = ApiResponse.success(user, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Get user by name GET/users/search?name=[name]
    @Operation(summary = "Buscar usuário por nome")
    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUserByName(@RequestParam String name) {
        List<UserResponseDTO> users= service.getUserByName(name);
        ApiResponse<List<UserResponseDTO>> response = ApiResponse.success(users, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Delete a user DELETE/users/[id]
    @Operation(summary = "Deletar usuário por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        ApiResponse<String> response = ApiResponse.success("User deleted successfully", HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
}
