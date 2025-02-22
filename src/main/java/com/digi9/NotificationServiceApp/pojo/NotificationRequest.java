package com.digi9.NotificationServiceApp.pojo;


public class NotificationRequest {
    private String projectId;
    private String title;
    private String message;
    private String imageUrl;
    private String userGroup;

    // Default Constructor
    public NotificationRequest() {}

    // Parameterized Constructor
    public NotificationRequest(String projectId, String title, String message, String imageUrl, String userGroup) {
        this.projectId = projectId;
        this.title = title;
        this.message = message;
        this.imageUrl = imageUrl;
        this.userGroup = userGroup;
    }

    // Getters and Setters
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "projectId='" + projectId + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", userGroup='" + userGroup + '\'' +
                '}';
    }
}

