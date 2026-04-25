package com.example.site_pl_99.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Применяем ко всем API
                .allowedOriginPatterns("*") // Разрешаем любые домены (включая ngrok)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*") // Разрешаем любые заголовки
                .exposedHeaders("Authorization", "ngrok-skip-browser-warning") // Показываем важные заголовки фронту
                .allowCredentials(true) // Разрешаем передачу Cookie и Auth-заголовков
                .maxAge(3600); // Кэшируем CORS-ответ на час, чтобы не спамить запросами
    }
}
