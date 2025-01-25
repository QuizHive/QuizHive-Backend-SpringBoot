package com.example.quizhive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the root endpoint of the application.
 */
@RestController
public class RootController {

    /**
     * Root endpoint for the application.
     * @return A welcome message for the QuizHive API.
     */
    @GetMapping("/")
    public String welcome() {
        return "Welcome to QuizHive API. Please refer to the documentation for proper usage.";
    }
}