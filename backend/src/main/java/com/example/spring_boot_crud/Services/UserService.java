package com.example.spring_boot_crud.Services;


import java.util.List;
import com.example.spring_boot_crud.Entities.User;


public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    List<User> getUserByName(String name);
    void updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
}

