package com.example.spring_boot_crud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permite todos os endpoints
                .allowedOrigins("http://localhost:3000")  // Permite o acesso apenas do frontend React local
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Permite apenas esses métodos HTTP
                .allowedHeaders("*");  // Permite todos os cabeçalhos
    }
}