package com.example.site_pl_99.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition(info = @Info(
        title = "site_pl_99",
        version = "1.0",
        description = "API для сайта лицея"
))
@Configuration
public class SwaggerConfig {
}
