package com.example.appointment_service.client;

import com.example.appointment_service.dto.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface NotificationClient {
    @PostMapping("/notifications")
    void sendNotification(@RequestBody NotificationDTO notification);
}
