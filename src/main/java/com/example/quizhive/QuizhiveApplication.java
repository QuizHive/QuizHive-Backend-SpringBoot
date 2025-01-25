//package com.example.quizhive;
//
//import com.example.quizhive.util.DatabaseInitializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@SpringBootApplication
//public class QuizhiveApplication {
//
//	private static final Logger logger = LoggerFactory.getLogger(QuizhiveApplication.class);
//
//	public static void main(String[] args) {
//		SpringApplication.run(QuizhiveApplication.class, args);
//		logger.info("QuizHive API is running...");
//	}
//
//	@Bean
//	public CommandLineRunner initDatabase(DatabaseInitializer databaseInitializer) {
//		return args -> {
//			databaseInitializer.initialize();
//			logger.info("Database initialized successfully.");
//		};
//	}
//}

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

