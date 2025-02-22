package com.digi9.NotificationServiceApp.pojo;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class NotificationLog {
	    private String logId;
	    private String notificationId;
	    private String fcmToken;
	    private String status;
	    private String failureReason;
	    private Timestamp sentAt;


	public Timestamp getSentAt() {
			return sentAt;
		}
		public void setSentAt(Timestamp sentAt) {
			this.sentAt = sentAt;
		}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	public String getFcmToken() {
		return fcmToken;
	}
	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	
}

