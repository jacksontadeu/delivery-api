package com.jtmjinfo.delivery_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Delivery API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Jackson Tadeu de Moraes",
                        email = "jacksontadeu@hotmail.com",
                        url = "https://github.com/jacksontadeu"
                )
        )
)
public class OpenApiConfiguration {
}


