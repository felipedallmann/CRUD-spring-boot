package com.example.spring_boot_crud.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_crud.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Search by name
    List<User> findByNameContainingIgnoreCase(String name);

    // Search by e-mail
    Optional<User> findByEmail(String email);
}
