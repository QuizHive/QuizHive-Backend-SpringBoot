////////package com.example.quizhive.controller;
////////
////////import com.example.quizhive.dto.LoginRequest;
////////import com.example.quizhive.dto.RegisterRequest;
////////import com.example.quizhive.dto.RefreshTokenRequest;
////////import com.example.quizhive.model.User;
////////import com.example.quizhive.service.AuthService;
////////import io.swagger.v3.oas.annotations.Operation;
////////import io.swagger.v3.oas.annotations.responses.ApiResponse;
////////import io.swagger.v3.oas.annotations.responses.ApiResponses;
////////import jakarta.validation.Valid;
////////import org.springframework.beans.factory.annotation.Autowired;
////////import org.springframework.http.ResponseEntity;
////////import org.springframework.web.bind.annotation.*;
////////
////////import java.util.Map;
////////
////////@RestController
////////@RequestMapping("/auth")
////////public class AuthController {
////////
////////    @Autowired
////////    private AuthService authService;
////////
////////    /**
////////     * Login endpoint.
////////     *
////////     * @param loginRequest Contains email and password.
////////     * @return Access and refresh tokens.
////////     */
////////    @Operation(summary = "Login user", description = "Authenticate a user and return access and refresh tokens.")
////////    @ApiResponses(value = {
////////            @ApiResponse(responseCode = "200", description = "Login successful"),
////////            @ApiResponse(responseCode = "401", description = "Invalid credentials")
////////    })
////////    @PostMapping("/login")
////////    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest loginRequest) {
////////        Map<String, String> tokens = authService.loginUser(loginRequest.getEmail(), loginRequest.getPasswordHash());
////////        return ResponseEntity.ok(tokens);
////////    }
////////
////////    /**
////////     * Register endpoint.
////////     *
////////     * @param registerRequest Contains user details for registration.
////////     * @return Success message.
////////     */
////////    @Operation(summary = "Register user", description = "Register a new user with email, password, nickname, and role.")
////////    @ApiResponses(value = {
////////            @ApiResponse(responseCode = "201", description = "User registered successfully"),
////////            @ApiResponse(responseCode = "409", description = "User already exists")
////////    })
////////    @PostMapping("/register")
////////    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequest registerRequest) {
////////        User user = authService.registerUser(
////////                registerRequest.getEmail(),
////////                registerRequest.getPasswordHash(),
////////                registerRequest.getNickname(),
////////                registerRequest.getRole()
////////        );
////////
////////        // Standardized response
////////        return ResponseEntity.status(201).body(Map.of(
////////                "success", true,
////////                "message", "User registered successfully",
////////                "data", Map.of("id", user.getId())
////////        ));
////////    }
////////
////////    /**
////////     * Refresh token endpoint.
////////     *
////////     * @param refreshTokenRequest Contains the refresh token.
////////     * @return A new access token.
////////     */
////////    @Operation(summary = "Refresh access token", description = "Generate a new access token using a valid refresh token.")
////////    @ApiResponses(value = {
////////            @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
////////            @ApiResponse(responseCode = "403", description = "Invalid refresh token")
////////    })
////////    @PostMapping("/refresh-token")
////////    public ResponseEntity<Map<String, Object>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
////////        String newAccessToken = authService.refreshToken(refreshTokenRequest.getToken());
////////
////////        // Standardized response
////////        return ResponseEntity.ok(Map.of(
////////                "success", true,
////////                "message", "Access token refreshed successfully",
////////                "data", Map.of("accessToken", newAccessToken)
////////        ));
////////    }
////////}
//////
//////package com.example.quizhive.controller;
//////
//////import com.example.quizhive.dto.LoginRequest;
//////import com.example.quizhive.dto.RegisterRequest;
//////import com.example.quizhive.dto.RefreshTokenRequest;
//////import com.example.quizhive.model.User;
//////import com.example.quizhive.service.AuthService;
//////import io.swagger.v3.oas.annotations.Operation;
//////import io.swagger.v3.oas.annotations.responses.ApiResponse;
//////import io.swagger.v3.oas.annotations.responses.ApiResponses;
//////import jakarta.validation.Valid;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.http.ResponseEntity;
//////import org.springframework.web.bind.annotation.*;
//////
//////import java.util.Map;
//////
//////@RestController
//////@RequestMapping("/auth")
//////public class AuthController {
//////
//////    @Autowired
//////    private AuthService authService;
//////
//////    @Operation(summary = "Login user", description = "Authenticate a user and return access and refresh tokens.")
//////    @ApiResponses(value = {
//////            @ApiResponse(responseCode = "200", description = "Login successful"),
//////            @ApiResponse(responseCode = "401", description = "Invalid credentials")
//////    })
//////    @PostMapping("/login")
//////    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest loginRequest) {
//////        Map<String, String> tokens = authService.loginUser(loginRequest.getEmail(), loginRequest.getPasswordHash());
//////        return ResponseEntity.ok(tokens);
//////    }
//////
//////    @Operation(summary = "Register user", description = "Register a new user with email, password, nickname, and role.")
//////    @ApiResponses(value = {
//////            @ApiResponse(responseCode = "201", description = "User registered successfully"),
//////            @ApiResponse(responseCode = "409", description = "User already exists")
//////    })
//////    @PostMapping("/register")
//////    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequest registerRequest) {
//////        User user = authService.registerUser(
//////                registerRequest.getEmail(),
//////                registerRequest.getPasswordHash(),
//////                registerRequest.getNickname(),
//////                registerRequest.getRole()
//////        );
//////        return ResponseEntity.status(201).body(Map.of(
//////                "success", true,
//////                "message", "User registered successfully",
//////                "data", Map.of("id", user.getId())
//////        ));
//////    }
//////
//////    @Operation(summary = "Refresh access token", description = "Generate a new access token using a valid refresh token.")
//////    @ApiResponses(value = {
//////            @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
//////            @ApiResponse(responseCode = "403", description = "Invalid refresh token")
//////    })
//////    @PostMapping("/refresh-token")
//////    public ResponseEntity<Map<String, Object>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
//////        String newAccessToken = authService.refreshToken(refreshTokenRequest.getToken());
//////        return ResponseEntity.ok(Map.of(
//////                "success", true,
//////                "message", "Access token refreshed successfully",
//////                "data", Map.of("accessToken", newAccessToken)
//////        ));
//////    }
//////}
////package com.example.quizhive.controller;
////
////import com.example.quizhive.model.User;
////import com.example.quizhive.service.AuthService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/api/auth")
////public class AuthController {
////
////    @Autowired
////    private AuthService authService;
////
////    @PostMapping("/register")
////    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
////        User user = authService.register(request.getEmail(), request.getPassword(), request.getName());
////        return ResponseEntity.status(201).body(user);
////    }
////
////    @PostMapping("/login")
////    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
////        String token = authService.login(request.getEmail(), request.getPassword());
////        return ResponseEntity.ok(new LoginResponse(token));
////    }
////}
//package com.example.quizhive.controller;
//
//import com.example.quizhive.dto.LoginRequest;
//import com.example.quizhive.dto.RegisterRequest;
//import com.example.quizhive.model.User;
//import com.example.quizhive.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    @PostMapping("/register")
//    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
//        User user = authService.register(request.getEmail(), request.getPassword(), request.getName());
//        return ResponseEntity.status(201).body(user);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {
//        String token = authService.login(request.getEmail(), request.getPassword());
//        return ResponseEntity.ok(token);
//    }
//}

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

    @Autowired
    private AuthService authService;

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
