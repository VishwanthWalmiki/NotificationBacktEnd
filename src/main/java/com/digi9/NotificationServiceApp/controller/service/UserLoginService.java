package com.digi9.NotificationServiceApp.controller.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digi9.NotificationServiceApp.pojo.LoginRequest;

@Service
public class UserLoginService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    // BCryptPasswordEncoder for verifying passwords
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String loginUser(LoginRequest request) {
        // Query to fetch the hashed password from the database
        String sql = "SELECT password FROM users WHERE email = ?";
        
        // Fetch the hashed password
        String hashedPassword = jdbcTemplate.queryForObject(sql, String.class, request.getEmail());
        
        System.out.println("hashedPassword from database :" +hashedPassword);
        System.out.println("request.getPassword() : "+request.getPassword());
        System.out.println("passwordEncoder.matches(request.getPassword(), hashedPassword :" +passwordEncoder.matches(request.getPassword(), hashedPassword));
        
        // Verify the password
        if (hashedPassword != null && passwordEncoder.matches(request.getPassword(), hashedPassword)) {
            // Generate a JWT token
            return jwtUtil.generateToken(request.getEmail());
        } else {
            return "Invalid email or password!";
        }
    }
}