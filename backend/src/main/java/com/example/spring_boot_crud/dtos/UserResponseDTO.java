package com.example.spring_boot_crud.dtos;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
}
