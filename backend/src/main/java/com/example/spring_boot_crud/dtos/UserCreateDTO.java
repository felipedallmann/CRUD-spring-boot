package com.example.spring_boot_crud.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;


}
