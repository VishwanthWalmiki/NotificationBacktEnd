package com.digi9.NotificationServiceApp.dynamicproject;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationRequest {
    private String projectId;
    private String title;
    private String body;
    private String image;
    private List<String> fcmTokens;
    
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<String> getFcmTokens() {
		return fcmTokens;
	}
	public void setFcmTokens(List<String> fcmTokens) {
		this.fcmTokens = fcmTokens;
	}
    
}
