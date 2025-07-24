package com.shopcloud.notification_mservice.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI emailServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ShopCloud - Notification Service")
                        .description("Handles email templates, logs and delivery via RabbitMQ + JavaMail")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("MrxSteve Developer")
                                .url("https://github.com/MrxSteve")
                        )
                )
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}

