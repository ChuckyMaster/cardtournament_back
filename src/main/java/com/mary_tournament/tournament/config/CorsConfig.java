package com.mary_tournament.tournament.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 🔹 Autorise toutes les routes
                        .allowedOrigins(
                                "http://localhost:5173",
                                "http://127.0.0.1:5173",
                                "http://localhost:8080",
                                "http://127.0.0.1:8080"
                        ) // 🔹 Autorise uniquement ces origines
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 🔹 Méthodes autorisées
                        .allowedHeaders("*") // 🔹 Tous les headers sont acceptés
                        .allowCredentials(true); // 🔹 Active les credentials
            }
        };
    }
}
