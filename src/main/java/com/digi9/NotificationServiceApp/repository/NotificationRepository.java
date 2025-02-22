package com.digi9.NotificationServiceApp.repository;

import com.digi9.NotificationServiceApp.pojo.NotificationModel;
import com.digi9.NotificationServiceApp.pojo.NotificationLog;

public interface NotificationRepository {
    void saveNotification(NotificationModel notification);
    void saveNotificationLog(NotificationLog log);
    void updateNotificationCounts(String notificationId, int successCount, int failureCount);
}

