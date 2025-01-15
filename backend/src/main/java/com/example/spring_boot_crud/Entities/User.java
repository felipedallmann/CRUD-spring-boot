package com.example.spring_boot_crud.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")  // defines table in DB
public class User {
    
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // generates ID automatically
    private long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email address")
    //TO DO implementar validação de email própria
    @Column(nullable = false, unique = true)  // Garante que o email seja único
    private String email;
}
