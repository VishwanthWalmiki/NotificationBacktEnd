package com.digi9.NotificationServiceApp.notificationsender;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.digi9.NotificationServiceApp.dynamicproject.FirebaseConfigfOR;
import com.digi9.NotificationServiceApp.pojo.NotificationLog;
import com.digi9.NotificationServiceApp.pojo.NotificationModel;
import com.digi9.NotificationServiceApp.repository.NotificationRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class NotificationSender {

    private final FirebaseConfigfOR firebaseConfig;
    private final NotificationRepository notificationRepository;

    public NotificationSender(FirebaseConfigfOR firebaseConfig, NotificationRepository notificationRepository) {
        this.firebaseConfig = firebaseConfig;
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(NotiReq request, String userId) {
        FirebaseMessaging messaging = firebaseConfig.getFirebaseMessaging(request.getProjectId());

        // Create Notification object
        NotificationModel notification = new NotificationModel();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setProjectId(request.getProjectId());
        notification.setTitle(request.getTitle());
        notification.setBody(request.getBody());
        notification.setImageUrl(request.getImageUrl());
        notification.setTotalUsers(request.getFcmTokens().size());
        notification.setUserId(userId);
        notification.setSuccessCount(0);
        notification.setFailureCount(0);

        notificationRepository.saveNotification(notification);

        int successCount = 0;
        int failureCount = 0;

        for (String token : request.getFcmTokens()) {
            try {
                Notification fcmNotification = Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getBody())
                        .setImage(request.getImageUrl())
                        .build();

                Message message = Message.builder()
                        .setNotification(fcmNotification)
                        .setToken(token)
                        .build();

                messaging.send(message);
                saveNotificationLog(notification.getNotificationId(), token, "SUCCESS", null);
                successCount++;

            } catch (FirebaseMessagingException e) {
                saveNotificationLog(notification.getNotificationId(), token, "FAILED", e.getMessage());
                failureCount++;
            }
        }

        // Update success and failure counts
        notificationRepository.updateNotificationCounts(notification.getNotificationId(), successCount, failureCount);
    }

    private void saveNotificationLog(String notificationId, String token, String status, String failureReason) {
        NotificationLog log = new NotificationLog();
        log.setLogId(UUID.randomUUID().toString());
        log.setNotificationId(notificationId);
        log.setFcmToken(token);
        log.setStatus(status);
        log.setFailureReason(failureReason);
        notificationRepository.saveNotificationLog(log);
    }
}
