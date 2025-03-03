package com.agenda.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API de Agenda de Contatos",
        version = "1.0",
        description = "API para gerenciamento de contatos pessoais",
        contact = @Contact(
            name = "Marshall Paiva",
            url = "https://www.linkedin.com/in/marshallpaiva/",
            email = "marshallpaiva@hotmail.com"
        )
    ),
    servers = {
        @Server(
            url = "/",
            description = "Servidor Local"
        )
    }
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class OpenApiConfig {
} 