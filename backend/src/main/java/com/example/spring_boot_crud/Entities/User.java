package com.example.spring_boot_crud.entities;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.spring_boot_crud.validation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")  // defines table in DB
@EntityListeners(AuditingEntityListener.class) // Habilita auditoria para esta entidade

public class User {
    
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // generates ID automatically
    private long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Email is required")
    @ValidEmail(message = "Invalid email address")
    @Column(nullable = false, unique = true)  // Garante que o email seja único
    private String email;

    @Column(nullable=false)
    private boolean deleted = false;
}
