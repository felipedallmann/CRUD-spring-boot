package com.example.spring_boot_crud.services;


import java.util.List;

import com.example.spring_boot_crud.dtos.UserCreateDTO;
import com.example.spring_boot_crud.dtos.UserResponseDTO;


public interface UserService {
    void createUser(UserCreateDTO user);
    void updateUser(Long id, UserCreateDTO updatedUser);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getUserByName(String name);
    void deleteUser(Long id);
    List<UserResponseDTO> getDeletedUser();
}

