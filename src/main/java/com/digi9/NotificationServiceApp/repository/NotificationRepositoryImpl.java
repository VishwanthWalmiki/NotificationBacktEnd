package com.digi9.NotificationServiceApp.repository;


import java.sql.Timestamp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.digi9.NotificationServiceApp.pojo.NotificationLog;
import com.digi9.NotificationServiceApp.pojo.NotificationModel;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    private final JdbcTemplate jdbcTemplate;

    public NotificationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveNotification(NotificationModel notification) {
        String sql = "INSERT INTO notifications " +
                "(notification_id, project_id, title, body, image_url, total_users, user_id, success_count, failure_count, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                notification.getNotificationId(),
                notification.getProjectId(),
                notification.getTitle(),
                notification.getBody(),
                notification.getImageUrl(),
                notification.getTotalUsers(),
                notification.getUserId(),
                notification.getSuccessCount(),
                notification.getFailureCount(),
                new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public void saveNotificationLog(NotificationLog log) {
        String sql = "INSERT INTO notification_logs " +
                "(log_id, notification_id, fcm_token, status, failure_reason, sent_at) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                log.getLogId(),
                log.getNotificationId(),
                log.getFcmToken(),
                log.getStatus(),
                log.getFailureReason(),
                new Timestamp(System.currentTimeMillis()));
    }
    
    @Override
    public void updateNotificationCounts(String notificationId, int successCount, int failureCount) {
        String sql = "UPDATE notifications " +
                     "SET success_count = ?, failure_count = ? " +
                     "WHERE notification_id = ?";
        
        jdbcTemplate.update(sql, successCount, failureCount, notificationId);
    }


}

