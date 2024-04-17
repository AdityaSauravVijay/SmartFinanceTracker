package com.smartfinancetracker;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply the policy to all endpoints
                .allowedOrigins("http://localhost:3000") // Allow these origins to access your endpoints
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // Specify which HTTP methods are allowed
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true).maxAge(3600); // Allow credentials and set max age
    }
}
