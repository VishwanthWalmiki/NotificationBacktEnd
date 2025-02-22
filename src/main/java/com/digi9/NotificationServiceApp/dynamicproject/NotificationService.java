package com.digi9.NotificationServiceApp.dynamicproject;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class NotificationService {

    private final FirebaseConfigfOR firebaseConfig;

    public NotificationService(FirebaseConfigfOR firebaseConfig) {
        this.firebaseConfig = firebaseConfig;
    }

    public void sendNotification(NotificationRequest request) {
        FirebaseMessaging messaging = firebaseConfig.getFirebaseMessaging(request.getProjectId());

        // Build Notification using the builder pattern
        Notification notification = Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getBody())
                .setImage(request.getImage()) // Only if image is provided
                .build();

        try {
            for (String token : request.getFcmTokens()) {
                Message message = Message.builder()
                        .setNotification(notification)
                        .putData("projectId", request.getProjectId()) // Additional custom data
                        .setToken(token)
                        .build();

                String response = messaging.send(message);
                System.out.println("Sent message with response: " + response);
            }
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException("Error sending FCM notification: " + e.getMessage(), e);
        }
    }


    public void fetchFirestoreData(String projectId) {
        Firestore firestore = firebaseConfig.getFirestore(projectId);
        try {
            firestore.collection("users").get().get()
                    .getDocuments()
                    .forEach(doc -> System.out.println("Fetched User: " + doc.getId()));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching Firestore data", e);
        }
    }
}
