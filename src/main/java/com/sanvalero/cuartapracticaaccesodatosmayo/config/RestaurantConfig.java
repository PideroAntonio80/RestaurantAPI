package com.sanvalero.cuartapracticaaccesodatosmayo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Configuration
public class RestaurantConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Restaurants")
                        .description("Restaurants API REST")
                        .contact(new Contact()
                                .name("Pedro Oros")
                                .email("shadycreek@hotmail.com")
                                .url("https://github.com/PideroAntonio80/RestaurantAPI.git"))
                        .version("1.0"));
    }
}

