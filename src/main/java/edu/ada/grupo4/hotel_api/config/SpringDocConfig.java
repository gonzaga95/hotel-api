package edu.ada.grupo4.hotel_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Hotel API"))
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "basic-auth",
        scheme = "basic")
public class SpringDocConfig {}