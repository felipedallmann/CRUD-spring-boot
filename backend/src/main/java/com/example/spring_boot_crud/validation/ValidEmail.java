package com.example.spring_boot_crud.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class) // Referência ao validador
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "Invalid email address";  // Mensagem padrão
    Class<?>[] groups() default {};  // Para grupos de validação
    Class<? extends Payload>[] payload() default {};  // Metadados adicionais
}
