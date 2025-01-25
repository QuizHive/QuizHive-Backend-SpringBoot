//////package com.example.quizhive.config;
//////
//////import com.example.quizhive.security.RateLimiterFilter;
//////import org.springframework.context.annotation.Bean;
//////import org.springframework.context.annotation.Configuration;
//////import org.springframework.security.authentication.AuthenticationManager;
//////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////import org.springframework.security.web.SecurityFilterChain;
//////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//////
//////@Configuration
//////public class SecurityConfig {
//////
//////    private final RateLimiterFilter rateLimiterFilter;
//////
//////    public SecurityConfig(RateLimiterFilter rateLimiterFilter) {
//////        this.rateLimiterFilter = rateLimiterFilter;
//////    }
//////
//////    @Bean
//////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////        http.csrf().disable()
//////                .authorizeHttpRequests()
//////                .requestMatchers("/auth/**").permitAll()
//////                .anyRequest().authenticated()
//////                .and()
//////                .addFilterBefore(rateLimiterFilter, UsernamePasswordAuthenticationFilter.class);
//////
//////        return http.build();
//////    }
//////
//////    @Bean
//////    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//////        return configuration.getAuthenticationManager();
//////    }
//////}
////package com.example.quizhive.config;
////
////import com.example.quizhive.security.RateLimiterFilter;
////import jakarta.servlet.http.HttpServletResponse;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.web.AuthenticationEntryPoint;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////public class SecurityConfig {
////
////    private final RateLimiterFilter rateLimiterFilter;
////
////    public SecurityConfig(RateLimiterFilter rateLimiterFilter) {
////        this.rateLimiterFilter = rateLimiterFilter;
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeHttpRequests()
////                .requestMatchers("/auth/**").permitAll() // Publicly accessible endpoints
////                .anyRequest().authenticated() // All other endpoints require authentication
////                .and()
////                .exceptionHandling()
////                .authenticationEntryPoint(authenticationEntryPoint()) // Handle unauthorized access
////                .accessDeniedHandler((request, response, accessDeniedException) -> {
////                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
////                    response.getWriter().write("Access Denied: You do not have the necessary permissions.");
////                })
////                .and()
////                .addFilterBefore(rateLimiterFilter, UsernamePasswordAuthenticationFilter.class); // Add rate limiter
////
////        return http.build();
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
////        return configuration.getAuthenticationManager();
////    }
////
////    @Bean
////    public AuthenticationEntryPoint authenticationEntryPoint() {
////        return (request, response, authException) -> {
////            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////            response.getWriter().write("Unauthorized: Please provide valid credentials.");
////        };
////    }
////}
//package com.example.quizhive.config;
//
//import com.example.quizhive.security.RateLimiterFilter;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SecurityConfig {
//
//    private final RateLimiterFilter rateLimiterFilter;
//
//    public SecurityConfig(RateLimiterFilter rateLimiterFilter) {
//        this.rateLimiterFilter = rateLimiterFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**").permitAll() // Publicly accessible endpoints
//                        .anyRequest().authenticated() // All other endpoints require authentication
//                )
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(authenticationEntryPoint()) // Handle unauthorized access
//                        .accessDeniedHandler((request, response, accessDeniedException) -> {
//                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                            response.getWriter().write("Access Denied: You do not have the necessary permissions.");
//                        })
//                )
//                .addFilterBefore(rateLimiterFilter, UsernamePasswordAuthenticationFilter.class) // Add rate limiter filter
//                .build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return (request, response, authException) -> {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Unauthorized: Please provide valid credentials.");
//        };
//    }
//}
