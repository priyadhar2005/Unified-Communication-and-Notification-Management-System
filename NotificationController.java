package com.notification.controller;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final List<Map<String, Object>> notifications = new ArrayList<>();

    @PostMapping("/schedule")
    public String scheduleNotification(@RequestBody NotificationRequest request) {

        Map<String, Object> notification = new HashMap<>();
        notification.put("title", request.getTitle());
        notification.put("message", request.getMessage());
        notification.put("recipient", request.getRecipient());
        notification.put("type", request.getType());
        notification.put("scheduleTime", request.getScheduleTime());

        notifications.add(notification);

        return "Notification Scheduled Successfully";
    }

    @GetMapping
    public List<Map<String, Object>> getNotifications() {
        return notifications;
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationRequest request) {

        if(request.getType().equalsIgnoreCase("EMAIL")){
            return "Email sent to " + request.getRecipient();
        }

        if(request.getType().equalsIgnoreCase("WHATSAPP")){
            return "WhatsApp message sent to " + request.getRecipient();
        }

        return "Unsupported Notification Type";
    }

    static class NotificationRequest {

        private String title;
        private String message;
        private String recipient;
        private String type;
        private LocalDateTime scheduleTime;

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

        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public LocalDateTime getScheduleTime() {
            return scheduleTime;
        }

        public void setScheduleTime(LocalDateTime scheduleTime) {
            this.scheduleTime = scheduleTime;
        }
    }
}
