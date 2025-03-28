package com.digi9.NotificationServiceApp.notificationsender;

import java.util.List;

public class NotiReq {
	
	private String projectId;
	private String title;
    private String body;
    private String imageUrl;
    private List<String> fcmTokens;
    
    
    public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public List<String> getFcmTokens() {
		return fcmTokens;
	}
	public void setFcmTokens(List<String> fcmTokens) {
		this.fcmTokens = fcmTokens;
	}
    
}

