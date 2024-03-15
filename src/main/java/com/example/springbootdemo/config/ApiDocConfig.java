package com.example.springbootdemo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/*@OpenAPIDefinition(
        info = @Info(
                title = "Simple online shop app rest api",
                version = "1.2",
                contact =
                @Contact(
                        name = "aka Gordon Freeman",
                        email = "noreply@gmail.com",
                        url = "https://github.com/rahmatilla1993"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://springdoc.org"),
                termsOfService = "http://swagger.io/terms/",
                description = "Description of Rest Api"
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring 6 Wiki Documentation",
                url = "https://springshop.wiki.github.org/docs"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Test-Server"
                )
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)*/
@Configuration
public class ApiDocConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PDP Online Java(SpringDOC)")
                        .description("This Document Designed For Teaching SpringDOC Project")
                        .version("10")
                        .contact(new Contact()
                                .name("Elmurodov Javohir")
                                .email("john.lgd65@gmail.com")
                                .url("https://github.com/jlkesh"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .termsOfService("http://swagger.io/terms/"))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Production Server"),
                        new Server().url("http://localhost:9090").description("Test Server")
                )).addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components((new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                ));
    }

    @Bean
    public GroupedOpenApi userGroupAPI() {
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/api/store/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminGroupAPI() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/api/item/**")
                .build();
    }

    @Bean
    public GroupedOpenApi authGroupAPI() {
        return GroupedOpenApi.builder()
                .group("auth")
                .pathsToMatch("/api/auth/**")
                .build();
    }
}
