//package com.example.quizhive.security;
//
//import io.github.bucket4j.Bandwidth;
//import io.github.bucket4j.Bucket;
//import io.github.bucket4j.Refill;
//import org.springframework.stereotype.Component;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.time.Duration;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class RateLimiterFilter implements Filter {
//
//    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();
//
//    @Override
//    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        String clientIp = getClientIp(request);
//
//        // Create a rate-limiting bucket for the client if not already created
//        Bucket bucket = buckets.computeIfAbsent(clientIp, this::newBucket);
//
//        // Try to consume a token
//        if (bucket.tryConsume(1)) {
//            chain.doFilter(request, response);
//        } else {
//            response.setStatus(HttpServletResponse.SC_TOO_MANY_REQUESTS); // 429 Too Many Requests
//            response.setHeader("Retry-After", "900"); // 900 seconds = 15 minutes
//            response.getWriter().write("Rate limit exceeded. Try again later.");
//        }
//    }
//
//    private Bucket newBucket(String clientIp) {
//        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofMinutes(15)));
//        return Bucket.builder().addLimit(limit).build();
//    }
//
//    private String getClientIp(HttpServletRequest request) {
//        String xfHeader = request.getHeader("X-Forwarded-For");
//        return (xfHeader != null && !xfHeader.isEmpty()) ? xfHeader.split(",")[0] : request.getRemoteAddr();
//    }
//}