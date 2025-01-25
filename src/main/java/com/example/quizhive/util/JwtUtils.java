////package com.example.quizhive.util;
////
////import com.example.quizhive.exception.ForbiddenException;
////import com.example.quizhive.exception.UnauthorizedException;
////import io.jsonwebtoken.*;
////import io.jsonwebtoken.security.Keys;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.stereotype.Component;
////
////import java.security.Key;
////import java.util.Date;
////
////@Component
////public class JwtUtils {
////
////    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
////
////    private final Key jwtSecretKey;
////    private final long accessExpirationMs;
////    private final long refreshExpirationMs;
////
////    public JwtUtils(
////            @Value("${jwt.secret}") String secret,
////            @Value("${jwt.access.expiration-ms}") long accessExpirationMs,
////            @Value("${jwt.refresh.expiration-ms}") long refreshExpirationMs
////    ) {
////        this.jwtSecretKey = Keys.hmacShaKeyFor(secret.getBytes());
////        this.accessExpirationMs = accessExpirationMs;
////        this.refreshExpirationMs = refreshExpirationMs;
////    }
////
////    /**
////     * Generate an access token.
////     *
////     * @param id The user ID.
////     * @return The JWT access token.
////     */
////    public String generateAccessToken(String id) {
////        return Jwts.builder()
////                .setSubject(id)
////                .claim("type", "access")
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + accessExpirationMs))
////                .signWith(jwtSecretKey)
////                .compact();
////    }
////
////    /**
////     * Generate a refresh token.
////     *
////     * @param id The user ID.
////     * @return The JWT refresh token.
////     */
////    public String generateRefreshToken(String id) {
////        return Jwts.builder()
////                .setSubject(id)
////                .claim("type", "refresh")
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationMs))
////                .signWith(jwtSecretKey)
////                .compact();
////    }
////
////    /**
////     * Verify a token and return the user ID.
////     *
////     * @param token The JWT token.
////     * @param type  The expected token type ("access" or "refresh").
////     * @return The user ID extracted from the token.
////     */
////    public String verifyToken(String token, String type) {
////        try {
////            Claims claims = Jwts.parserBuilder()
////                    .setSigningKey(jwtSecretKey)
////                    .build()
////                    .parseClaimsJws(token)
////                    .getBody();
////
////            if (!type.equals(claims.get("type", String.class))) {
////                throw new UnauthorizedException("Invalid token type");
////            }
////
////            logger.info("Token verified successfully for user ID: {}", claims.getSubject());
////            return claims.getSubject(); // Return the user ID
////        } catch (ExpiredJwtException e) {
////            logger.error("Token has expired: {}", e.getMessage());
////            throw new UnauthorizedException("Token has expired");
////        } catch (JwtException | IllegalArgumentException e) {
////            logger.error("Invalid token: {}", e.getMessage());
////            throw new UnauthorizedException("Invalid token");
////        }
////    }
////
////    /**
////     * Check if a token is expired.
////     *
////     * @param token The JWT token.
////     * @return True if the token is expired, otherwise false.
////     */
////    public boolean isTokenExpired(String token) {
////        try {
////            Jwts.parserBuilder()
////                    .setSigningKey(jwtSecretKey)
////                    .build()
////                    .parseClaimsJws(token);
////            return false;
////        } catch (ExpiredJwtException e) {
////            return true;
////        }
////    }
////
////    /**
////     * Refresh an access token using a refresh token.
////     *
////     * @param refreshToken The refresh token.
////     * @return A new access token.
////     */
////    public String refreshAccessToken(String refreshToken) {
////        try {
////            String userId = verifyToken(refreshToken, "refresh");
////            return generateAccessToken(userId);
////        } catch (Exception e) {
////            logger.error("Failed to refresh access token: {}", e.getMessage());
////            throw new ForbiddenException("Invalid refresh token");
////        }
////    }
////}
//package com.example.quizhive.util;
//
//import io.jsonwebtoken.*;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//
//    private static final String SECRET_KEY = "mysecretkey"; // Replace with your actual secret
//    private static final long EXPIRATION_TIME = 900000; // 15 minutes
//
//    public String generateToken(User user) {
//        return Jwts.builder()
//                .setSubject(user.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//
//    public UserDetails getUserDetailsFromToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//        String username = claims.getSubject();
//        return User.withUsername(username).password("").authorities("ROLE_USER").build();
//    }
//
//    public Authentication getAuthentication(UserDetails userDetails) {
//        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//    }
//
//    public String extractTokenFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}
package com.example.quizhive.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final String SECRET_KEY = "mysecretkey"; // Replace with your actual secret
    private static final long ACCESS_TOKEN_EXPIRATION = 900000; // 15 minutes
    private static final long REFRESH_TOKEN_EXPIRATION = 2592000000L; // 30 days

    /**
     * Generate an access token.
     * @param userId The ID of the user.
     * @return The access token.
     */
    public String generateAccessToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * Generate a refresh token.
     * @param userId The ID of the user.
     * @return The refresh token.
     */
    public String generateRefreshToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * Validate a JWT token.
     * @param token The token to validate.
     * @return True if valid, false otherwise.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Extract user ID from a token.
     * @param token The token.
     * @return The user ID.
     */
    public String extractUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
