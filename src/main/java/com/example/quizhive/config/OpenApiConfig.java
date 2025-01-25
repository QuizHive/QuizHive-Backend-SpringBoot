package com.example.quizhive.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("QuizHive API Documentation")
                        .version("1.0.0")
                        .description("API documentation for QuizHive")
                        .license(new License().name("Copyright QuizHive 2025").url("https://github.com/QuizHive/QuizHive-Backend"))
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080/api/v1").description("Local Development Server"),
                        new Server().url("http://quizhive.ahmz.ir:3000/api/v1").description("Remote Development Server")
                ));
    }
}