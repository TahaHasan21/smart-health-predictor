package com.example.appointment_service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private Long userId;
    private String message;
}
