package com.example.appointment_service.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SymptomRequestDTO {
    private List<String> symptoms;
}
