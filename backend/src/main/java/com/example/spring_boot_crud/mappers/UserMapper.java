package com.example.spring_boot_crud.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.spring_boot_crud.dtos.UserCreateDTO;
import com.example.spring_boot_crud.dtos.UserResponseDTO;
import com.example.spring_boot_crud.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //DTO to entity
    @Mapping(target = "id", ignore = true)  // Ignora o mapeamento do id
    @Mapping(target = "deleted", ignore = true)
    User toEntity(UserCreateDTO dto);

    //Entity to DTO
    UserResponseDTO toResponseDTO(User user);
}
