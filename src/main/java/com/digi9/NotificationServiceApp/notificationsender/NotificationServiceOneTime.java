package com.digi9.NotificationServiceApp.notificationsender;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digi9.NotificationServiceApp.repository.SubscriptionRepository;

@Service
public class NotificationServiceOneTime {
	
	@Autowired
	NotificationSender notificationSender ;
	
	@Autowired
	ModelMapper map;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public void sendNotification(String userId, NotiReq req ) {
    	
        // Fetch the user's subscription
        Subscription subscription = subscriptionRepository.findByUserId(userId);

        if (subscription == null) {
            throw new RuntimeException("No active subscription found for user: " + userId);
        }

        // Check if the user has reached their notification limit
        if (subscription.getMaxNotifications() != null &&
            subscription.getNotificationCount() >= subscription.getMaxNotifications()) {
            throw new RuntimeException("Notification limit reached. Upgrade your plan to send more notifications.");
        }

        // Increment the notification count
        subscriptionRepository.updateNotificationCount(userId);
        

        // Call the notification sending logic (not implemented here)
        System.out.println("Notification sent to user " + userId + ": " + req.getBody());
        
        
        notificationSender.sendNotification(req,userId);
        
        
        
    }

	
}
