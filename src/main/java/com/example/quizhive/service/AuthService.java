package com.example.quizhive.service;

import com.example.quizhive.exception.ConflictException;
import com.example.quizhive.exception.NotFoundException;
import com.example.quizhive.exception.UnauthorizedException;
import com.example.quizhive.model.User;
import com.example.quizhive.repository.UserRepository;
import com.example.quizhive.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Register a new user.
     * @param email The email of the user.
     * @param passHash The hashed password of the user.
     * @param nickname The nickname of the user.
     * @param role The role of the user (e.g., admin, user).
     * @return The newly created user.
     */
    public User registerUser(String email, String passHash, String nickname, String role) {
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException("User already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassHash(passHash);
        user.setNickname(nickname);
        user.setRole(role);
        user.setScore(0);
        user.setCreatedAt(new Date());

        return userRepository.save(user);
    }

    /**
     * Log in a user and generate tokens.
     * @param email The email of the user.
     * @param passHash The hashed password of the user.
     * @return A token response containing refresh and access tokens.
     */
    public TokenResponse loginUser(String email, String passHash) {
        User user = userRepository.findByEmail(email);
        if (user == null || !user.getPassHash().equals(passHash)) {
            throw new UnauthorizedException("Invalid credentials");
        }

        String refreshToken = jwtUtils.generateRefreshToken(user.getId());
        String accessToken = jwtUtils.generateAccessToken(user.getId());

        return new TokenResponse(accessToken, refreshToken);
    }

    /**
     * Refresh the access token using a refresh token.
     * @param refreshToken The refresh token.
     * @return A new access token.
     */
    public TokenResponse refreshToken(String refreshToken) {
        String userId = jwtUtils.extractUserIdFromToken(refreshToken);
        if (userId == null) {
            throw new UnauthorizedException("Invalid refresh token");
        }
        String accessToken = jwtUtils.generateAccessToken(userId);
        return new TokenResponse(accessToken, refreshToken);
    }

    /**
     * TokenResponse class to encapsulate access and refresh tokens.
     */
    public static class TokenResponse {
        private String accessToken;
        private String refreshToken;

        public TokenResponse(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
}
