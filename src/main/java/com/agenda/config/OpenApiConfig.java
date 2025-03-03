package com.agenda.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Agenda")
                        .version("1.0")
                        .description("API REST para gerenciamento de contatos em uma agenda")
                        .contact(new Contact()
                                .name("Marshall Paiva")
                                .email("marshallpaiva@hotmail.com")));
    }
} 