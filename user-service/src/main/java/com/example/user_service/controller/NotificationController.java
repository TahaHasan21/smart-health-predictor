package com.example.user_service.controller;

import com.example.user_service.entity.Notification;
import com.example.user_service.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notification.setIsRead(false);
        return ResponseEntity.ok(notificationRepository.save(notification));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationRepository.findByUserIdOrderByCreatedAtDesc(userId));
    }

    @PutMapping("/{id}/mark-read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification != null) {
            notification.setIsRead(true);
            notificationRepository.save(notification);
        }
        return ResponseEntity.noContent().build();
    }
}
