package com.example.spring_boot_crud.dtos;

import com.example.spring_boot_crud.validation.ValidEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    // @Email(message = "Invalid email address")
    @ValidEmail(message = "Invalid email format")
    private String email;


}
