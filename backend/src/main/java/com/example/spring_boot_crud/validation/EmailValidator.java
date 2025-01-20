package com.example.spring_boot_crud.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    // Expressão regular mais robusta para validar e-mails
    private static final String EMAIL_REGEX =
            "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return false;  // Caso o e-mail seja nulo ou vazio
        }
        return Pattern.matches(EMAIL_REGEX, email);  // Valida com a regex
    }
}
