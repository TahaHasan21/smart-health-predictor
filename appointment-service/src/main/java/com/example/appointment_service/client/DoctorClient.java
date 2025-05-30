package com.example.appointment_service.client;

import com.example.appointment_service.dto.DoctorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service")
public interface DoctorClient {
    @GetMapping("/doctors/{id}")
    DoctorDTO getDoctorById(@PathVariable Long id);
}