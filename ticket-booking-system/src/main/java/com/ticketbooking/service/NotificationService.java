package com.ticketbooking.service;

import java.util.List;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.entity.Notification;

public interface NotificationService {
    ApiResponse<List<Notification>> getUserNotifications(Long userId);
    ApiResponse<String> markAsRead(Long notificationId);
    ApiResponse<Long> getUnreadCount(Long userId);
    void sendNotification(Long userId, String title, String message, String type);
}