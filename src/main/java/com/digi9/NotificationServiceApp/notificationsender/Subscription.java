package com.digi9.NotificationServiceApp.notificationsender;


import java.util.Date;

public class Subscription {
    private String subscriptionId;
    private String userId;
    private String planName;
    private String status;
    private Date startDate;
    private Date endDate;
    private Date renewalDate;
    private String paymentMethod;
    private String billingCycle;
    private double amount;
    private boolean trialPeriod;
    private Date cancellationDate;
    private Integer maxNotifications;
    private Integer notificationCount;

    // Getters and Setters
    public String getSubscriptionId() { return subscriptionId; }
    public void setSubscriptionId(String subscriptionId) { this.subscriptionId = subscriptionId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public Date getRenewalDate() { return renewalDate; }
    public void setRenewalDate(Date renewalDate) { this.renewalDate = renewalDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getBillingCycle() { return billingCycle; }
    public void setBillingCycle(String billingCycle) { this.billingCycle = billingCycle; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public boolean isTrialPeriod() { return trialPeriod; }
    public void setTrialPeriod(boolean trialPeriod) { this.trialPeriod = trialPeriod; }
    public Date getCancellationDate() { return cancellationDate; }
    public void setCancellationDate(Date cancellationDate) { this.cancellationDate = cancellationDate; }
    public Integer getMaxNotifications() { return maxNotifications; }
    public void setMaxNotifications(Integer maxNotifications) { this.maxNotifications = maxNotifications; }
    public Integer getNotificationCount() { return notificationCount; }
    public void setNotificationCount(Integer notificationCount) { this.notificationCount = notificationCount; }
}