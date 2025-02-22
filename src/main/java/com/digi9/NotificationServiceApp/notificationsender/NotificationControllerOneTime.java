package com.digi9.NotificationServiceApp.notificationsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digi9.NotificationServiceApp.controller.service.JwtUtil;

@RestController
@RequestMapping("/api/notifications")
public class NotificationControllerOneTime {

    @Autowired
    private NotificationServiceOneTime notificationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/send")
    public String sendNotification(@RequestHeader("Authorization") String token, @RequestBody NotiReq req) {
        try {
            // Extract token without 'Bearer ' prefix
            String jwt = token.replace("Bearer ", "");

            // Parse JWT to extract email
            String email = jwtUtil.parseToken(jwt).getSubject();

            // Query database to get user_id and project_id using email
            String sql = "SELECT user_id FROM users WHERE email = ?";
            try {
                var result = jdbcTemplate.queryForMap(sql, email);
                
                String userId = String.valueOf(result.get("user_id"));


                // Call notification service with extracted details
                notificationService.sendNotification(userId, req);
                
                return "Notification sent successfully!";
                
            } catch (EmptyResultDataAccessException e) {
                return "User not found for email: " + email;
            }

        } catch (Exception e) {
            return "Failed to send notification: " + e.getMessage();
        }
    }
}
