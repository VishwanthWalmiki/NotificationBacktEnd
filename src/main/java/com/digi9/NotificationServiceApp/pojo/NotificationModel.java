package com.digi9.NotificationServiceApp.pojo;


import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class NotificationModel {
    private String notificationId;
    private String projectId;
    private String title;
    private String body;
    private String imageUrl;
    private int totalUsers;
    private String userId;
    private int successCount;
    private int failureCount;
    private Timestamp createdAt;

    
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public int getFailureCount() {
		return failureCount;
	}
	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = (Timestamp) createdAt;
	}
}

