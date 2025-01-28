package com.example.quizhive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

@SpringBootApplication
public class QuizhiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizhiveApplication.class, args);
		System.out.println(new Date() + " - INFO - QuizHive API has started successfully.");
	}

	/**
	 * Configure CORS settings globally.
	 * @return WebMvcConfigurer to add CORS mappings.
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.exposedHeaders("Authorization");
				System.out.println(new Date() + " - INFO - CORS configuration initialized.");
			}
		};
	}

	/**
	 * Simulate initialization of resources on application startup.
	 * @return A CommandLineRunner to log initialization.
	 */
	@Bean
	public CommandLineRunner initializeResources() {
		return args -> {
			System.out.println(new Date() + " - INFO - Application resources have been initialized.");
		};
	}
}

