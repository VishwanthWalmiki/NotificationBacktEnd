package com.digi9.NotificationServiceApp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.digi9.NotificationServiceApp.notificationsender.Subscription;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SubscriptionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map the result set to the Subscription object
    private static final RowMapper<Subscription> SUBSCRIPTION_ROW_MAPPER = new RowMapper<Subscription>() {
        @Override
        public Subscription mapRow(ResultSet rs, int rowNum) throws SQLException {
            Subscription subscription = new Subscription();
            subscription.setSubscriptionId(rs.getString("subscription_id"));
            subscription.setUserId(rs.getString("user_id"));
            subscription.setPlanName(rs.getString("plan_name"));
            subscription.setStatus(rs.getString("status"));
            subscription.setStartDate(rs.getDate("start_date"));
            subscription.setEndDate(rs.getDate("end_date"));
            subscription.setRenewalDate(rs.getDate("renewal_date"));
            subscription.setPaymentMethod(rs.getString("payment_method"));
            subscription.setBillingCycle(rs.getString("billing_cycle"));
            subscription.setAmount(rs.getDouble("amount"));
            subscription.setTrialPeriod(rs.getBoolean("trial_period"));
            subscription.setCancellationDate(rs.getDate("cancellation_date"));
            subscription.setMaxNotifications(rs.getInt("max_count"));
            subscription.setNotificationCount(rs.getInt("current_count"));
            return subscription;
        }
    };

    public Subscription findByUserId(String userId) {
        String sql = "SELECT * FROM subscriptions WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, SUBSCRIPTION_ROW_MAPPER, userId);
    }

    public void updateNotificationCount(String userId) {
        String sql = "UPDATE subscriptions SET current_count = current_count + 1 WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }
}
