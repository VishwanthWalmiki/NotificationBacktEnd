package com.digi9.NotificationServiceApp.dynamicproject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Component
public class FirebaseConfigfOR {

    private final Map<String, FirebaseApp> firebaseAppCache = new ConcurrentHashMap<>();

    // Get Google credentials from service account
    private GoogleCredentials getCredentials() throws IOException {
        try (FileInputStream serviceAccount = new FileInputStream(
                "D:\\working 1 apps\\A\\NotificationServiceApp\\src\\main\\resources\\liveactivity-2756c-firebase-adminsdk-izwa7-7e259915ee.json")) {
            return GoogleCredentials.fromStream(serviceAccount);
        } 
    }

    /**
     * Get FirebaseMessaging instance for a specific project.
     */
    public FirebaseMessaging getFirebaseMessaging(String projectId) {
        FirebaseApp firebaseApp = firebaseAppCache.computeIfAbsent(projectId, this::initializeFirebaseApp);
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    /**
     * Get Firestore instance for a specific project.
     */
    public Firestore getFirestore(String projectId) {
        try {
            GoogleCredentials credentials = getCredentials();
            FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
                    .setCredentials(credentials)
                    .setProjectId(projectId)
                    .build();
            return firestoreOptions.getService();
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Firestore for project: " + projectId, e);
        }
    }

    /**
     * Initialize FirebaseApp for the given project, checking for duplicates.
     */
    private FirebaseApp initializeFirebaseApp(String projectId) {
        String appName = projectId + "-firebase";

        // Check if a FirebaseApp with the same name already exists
        for (FirebaseApp app : FirebaseApp.getApps()) {
            if (app.getName().equals(appName)) {
                return app; // Return existing instance
            }
        }

        // Create a new FirebaseApp if not found
        try {
            GoogleCredentials credentials = getCredentials();
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setProjectId(projectId)
                    .build();

            FirebaseApp newApp = FirebaseApp.initializeApp(options, appName);
            firebaseAppCache.put(projectId, newApp);
            return newApp;
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Firebase App for project: " + projectId, e);
        }
    }
}
