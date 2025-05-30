package com.example.appointment_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.appointment_service.dto.SymptomRequestDTO;

@FeignClient(name = "ml-predictor-service")
public interface MLClient {

    @PostMapping("/predict")
    String getPrediction(@RequestBody SymptomRequestDTO request);
}
