package com.example.spring_boot_crud.Entities;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    @JsonIgnore
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;
}
