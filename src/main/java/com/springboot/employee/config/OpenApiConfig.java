
// this class is used to configure Othis class juste penAPI for the Employee API wish is documented with Swagger
// so we added this class just to add a feild for token in the swagger UI


package com.springboot.employee.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Employee API", version = "v1"))
@SecurityScheme(
        name = "bearerAuth", // Un nom que nous choisirons pour faire référence à ce schéma
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP, // Le type de sécurité est HTTP
        bearerFormat = "JWT", // Le format du token est JWT
        in = SecuritySchemeIn.HEADER // Le token se trouve dans l'en-tête (Header)
)
public class OpenApiConfig {
}