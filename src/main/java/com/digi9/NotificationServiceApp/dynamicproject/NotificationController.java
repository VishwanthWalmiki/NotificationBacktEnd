package com.digi9.NotificationServiceApp.dynamicproject;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        notificationService.sendNotification(request);
        return ResponseEntity.ok("Notification sent successfully");
    }

    @GetMapping("/fetch/{projectId}")
    public ResponseEntity<String> fetchFirestoreData(@PathVariable String projectId) {
    	
    	System.out.println("rewquest received for fetchFirestoreData");
        notificationService.fetchFirestoreData(projectId);
        return ResponseEntity.ok("Fetched Firestore data");
    }
}
