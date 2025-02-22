package com.digi9.NotificationServiceApp.controller.service;



import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digi9.NotificationServiceApp.pojo.RegiterRequest;

@Service
public class RegisterService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder ;
    


    public void registerUser(RegiterRequest request) {
        // Insert into users table
        String insertUserSql = "INSERT INTO users (name, lastName, email, password) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, request.getName());
            ps.setString(2, request.getLastName());
            ps.setString(3, request.getEmail());
            ps.setString(4, hashedPassword);
            return ps;
        }, keyHolder);

        // Retrieve the generated user_id
        int userId = keyHolder.getKey().intValue();
        request.setUser_id(userId);

        // Insert into user_projects table
        List<String> projectIds = request.getProjectIds();
        if (projectIds != null && !projectIds.isEmpty()) {
            String insertProjectSql = "INSERT INTO user_projects (user_id, apps) VALUES (?, ?)";
            for (String projectId : projectIds) {
                jdbcTemplate.update(insertProjectSql, userId, projectId);
            }
        }
    }
}