package com.example.spring_boot_crud.config;

import org.springdoc.core.annotations.EnableOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableOpenApi
@EnableWebMvc
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("User API")
                .description("API para gerenciamento de usuários")
                .version("1.0"));
    }
}