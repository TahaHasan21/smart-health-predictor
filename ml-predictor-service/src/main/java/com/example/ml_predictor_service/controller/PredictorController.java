package com.example.ml_predictor_service.controller;

import com.example.ml_predictor_service.dto.SymptomRequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/predict")
public class PredictorController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PredictorController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping
    public String predictDisease(@RequestBody SymptomRequestDTO request) {
        String flaskUrl = "http://localhost:5000/predict";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<SymptomRequestDTO> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, entity, String.class);

            JsonNode root = objectMapper.readTree(response.getBody());
            return "Prediction: " + root.get("prediction").asText();

        } catch (Exception e) {
            return "Error calling ML service: " + e.getMessage();
        }
    }
}
