package com.digi9.NotificationServiceApp.controller.service;


import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // Secret key for signing the JWT (use a secure key in production)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Expiration time for the JWT (e.g., 1 hour)
    private static final long EXPIRATION_TIME = 3600000; // 1 hour in milliseconds

    // Generate a JWT token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Set the subject (e.g., user email)
                .setIssuedAt(new Date()) // Set the issue time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set the expiration time
                .signWith(SECRET_KEY) // Sign the token with the secret key
                .compact(); // Build the token
    }

    // Validate and parse the JWT token
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Set the secret key for validation
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}