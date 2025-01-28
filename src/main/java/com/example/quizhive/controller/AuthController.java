
package com.example.quizhive.controller;

import com.example.quizhive.dto.LoginRequest;
import com.example.quizhive.dto.RefreshTokenRequest;
import com.example.quizhive.dto.RegisterRequest;
import com.example.quizhive.model.User;
import com.example.quizhive.service.AuthService;
import com.example.quizhive.service.AuthService.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Register a new user.
     * @param request The registration request payload.
     * @return A success response with the user ID.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.registerUser(
                request.getEmail(),
                request.getPasswordHash(),
                request.getNickname(),
                request.getRole()
        );
        return ResponseEntity.status(201).body(Map.of(
                "success", true,
                "message", "User registered successfully",
                "data", Map.of("id", user.getId())
        ));
    }

    /**
     * Log in an existing user.
     * @param request The login request payload.
     * @return A response containing access and refresh tokens.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        TokenResponse tokens = authService.loginUser(
                request.getEmail(),
                request.getPasswordHash()
        );
        return ResponseEntity.ok(tokens);
    }

    /**
     * Refresh the access token using a valid refresh token.
     * @param request The refresh token request payload.
     * @return A response containing the new access token.
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, Object>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        TokenResponse tokens = authService.refreshToken(request.getToken());
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Access token refreshed successfully",
                "data", Map.of("accessToken", tokens.getAccessToken())
        ));
    }
}
