package com.example.spring_boot_crud.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_crud.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByDeletedFalse();
    Optional<User> findByIdAndDeletedFalse(Long id);
    
    // Search by name
    List<User> findByNameContainingIgnoreCaseAndDeletedFalse(String name);

    // Search by e-mail
    Optional<User> findByEmailAndDeletedFalse(String email);
}
