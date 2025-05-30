package com.example.appointment_service.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponseDTO {
    private Long id;
    private Long userId;
    private String userName;
    private Long doctorId;
    private String doctorName;
    private LocalDate appointmentDate;
    private String notes;
    private String prediction;
}