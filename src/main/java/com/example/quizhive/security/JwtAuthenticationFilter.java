//package com.example.quizhive.security;
//
//import com.example.quizhive.util.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws IOException {
//        // Extract Authorization Header
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
//            if (jwtUtils.validateToken(token)) {
//                UserDetails userDetails = jwtUtils.getUserDetailsFromToken(token);
//                SecurityContextHolder.getContext().setAuthentication(jwtUtils.getAuthentication(userDetails));
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
