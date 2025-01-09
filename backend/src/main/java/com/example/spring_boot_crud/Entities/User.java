package com.example.spring_boot_crud.Entities;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private String email;
}
