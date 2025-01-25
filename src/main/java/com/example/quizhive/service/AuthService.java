//////package com.example.quizhive.service;
//////
//////import com.example.quizhive.exception.ConflictException;
//////import com.example.quizhive.exception.NotFoundException;
//////import com.example.quizhive.exception.UnauthorizedException;
//////import com.example.quizhive.model.User;
//////import com.example.quizhive.repository.UserRepository;
//////import com.example.quizhive.util.JwtUtils;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Service;
//////
//////import java.util.HashMap;
//////import java.util.Map;
//////
//////@Service
//////public class AuthService {
//////
//////    @Autowired
//////    private UserRepository userRepository;
//////
//////    @Autowired
//////    private JwtUtils jwtUtils;
//////
//////    /**
//////     * Register a new user.
//////     *
//////     * @param email        User email
//////     * @param passwordHash User password hash
//////     * @param nickname     User nickname
//////     * @param role         User role
//////     * @return The registered user
//////     */
//////    public User registerUser(String email, String passwordHash, String nickname, String role) {
//////        if (userRepository.existsByEmail(email)) {
//////            throw new ConflictException("User already exists");
//////        }
//////        User user = new User(email, passwordHash, nickname, role, 0, new java.util.Date());
//////        return userRepository.save(user);
//////    }
//////
//////    /**
//////     * Authenticate a user and generate tokens.
//////     *
//////     * @param email        User email
//////     * @param passwordHash User password hash
//////     * @return A map containing access and refresh tokens
//////     */
//////    public Map<String, String> loginUser(String email, String passwordHash) {
//////        User user = userRepository.findByEmail(email)
//////                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));
//////        if (!user.getPasswordHash().equals(passwordHash)) {
//////            throw new UnauthorizedException("Invalid credentials");
//////        }
//////
//////        // Generate both access and refresh tokens
//////        String accessToken = jwtUtils.generateAccessToken(String.valueOf(user.getId()));
//////        String refreshToken = jwtUtils.generateRefreshToken(String.valueOf(user.getId()));
//////
//////        // Return tokens as a map
//////        Map<String, String> tokens = new HashMap<>();
//////        tokens.put("accessToken", accessToken);
//////        tokens.put("refreshToken", refreshToken);
//////
//////        return tokens;
//////    }
//////
//////    /**
//////     * Refresh the access token using a refresh token.
//////     *
//////     * @param refreshToken The refresh token
//////     * @return A new access token
//////     */
//////    public String refreshToken(String refreshToken) {
//////        return jwtUtils.refreshAccessToken(refreshToken);
//////    }
//////}
////
////package com.example.quizhive.service;
////
////import com.example.quizhive.exception.ConflictException;
////import com.example.quizhive.exception.UnauthorizedException;
////import com.example.quizhive.model.User;
////import com.example.quizhive.repository.UserRepository;
////import com.example.quizhive.util.JwtUtils;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.stereotype.Service;
////
////@Service
////public class AuthService {
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private PasswordEncoder passwordEncoder;
////
////    @Autowired
////    private JwtUtils jwtUtils;
////
////    public User register(String email, String password, String name) {
////        if (userRepository.existsByEmail(email)) {
////            throw new ConflictException("User already exists");
////        }
////
////        User user = new User();
////        user.setEmail(email);
////        user.setPassword(passwordEncoder.encode(password));
////        user.setName(name);
////        user.setCreatedAt(new java.util.Date());
////        return userRepository.save(user);
////    }
////
////    public String login(String email, String password) {
////        User user = userRepository.findByEmail(email)
////                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));
////
////        if (!passwordEncoder.matches(password, user.getPassword())) {
////            throw new UnauthorizedException("Invalid credentials");
////        }
////
////        return jwtUtils.generateToken(user);
////    }
////
////    public boolean verifyToken(String token) {
////        return jwtUtils.validateToken(token);
////    }
////}
//package com.example.quizhive.service;
//
//import com.example.quizhive.exception.ConflictException;
//import com.example.quizhive.exception.UnauthorizedException;
//import com.example.quizhive.model.User;
//import com.example.quizhive.repository.UserRepository;
//import com.example.quizhive.util.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    public User register(String email, String password, String name) {
//        if (userRepository.existsByEmail(email)) {
//            throw new ConflictException("Email already in use");
//        }
//        User user = new User(name, email, passwordEncoder.encode(password), List.of("ROLE_USER"), new Date(), 0);
//        return userRepository.save(user);
//    }
//
//    public String login(String email, String password) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));
//
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new UnauthorizedException("Invalid credentials");
//        }
//
//        return jwtUtils.generateToken(user);
//    }
//}
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
