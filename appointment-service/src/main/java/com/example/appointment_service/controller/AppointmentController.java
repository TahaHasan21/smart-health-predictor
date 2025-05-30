package com.example.appointment_service.controller;

import com.example.appointment_service.client.DoctorClient;
import com.example.appointment_service.client.MLClient;
import com.example.appointment_service.client.UserClient;
import com.example.appointment_service.dto.AppointmentResponseDTO;
import com.example.appointment_service.dto.DoctorDTO;
import com.example.appointment_service.dto.SymptomRequestDTO;
import com.example.appointment_service.dto.UserDTO;
import com.example.appointment_service.entity.Appointment;
import com.example.appointment_service.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private DoctorClient doctorClient;

    @Autowired
    private MLClient mlClient;



    @PostMapping
    public AppointmentResponseDTO bookAppointment(@RequestBody Appointment appointment) {
        appointment = appointmentRepository.save(appointment);

        UserDTO user = userClient.getUserById(appointment.getUserId());
        DoctorDTO doctor = doctorClient.getDoctorById(appointment.getDoctorId());

        // For now: hardcoded symptom list for testing
        List<String> symptoms = Arrays.asList("fever", "cough");

        // Call ML Predictor
        String prediction = mlClient.getPrediction(new SymptomRequestDTO(symptoms));

        return AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .userId(user.getId())
                .userName(user.getName())
                .doctorId(doctor.getId())
                .doctorName(doctor.getName())
                .appointmentDate(appointment.getAppointmentDate())
                .notes(appointment.getNotes())
                .prediction(prediction)
                .build();
    }


    @GetMapping
    public List<AppointmentResponseDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream().map(appointment -> {
            UserDTO user = userClient.getUserById(appointment.getUserId());
            DoctorDTO doctor = doctorClient.getDoctorById(appointment.getDoctorId());

            return AppointmentResponseDTO.builder()
                    .id(appointment.getId())
                    .userId(user.getId())
                    .userName(user.getName())
                    .doctorId(doctor.getId())
                    .doctorName(doctor.getName())
                    .appointmentDate(appointment.getAppointmentDate())
                    .notes(appointment.getNotes())
                    .build();
        }).collect(Collectors.toList());
    }


    @GetMapping("/user/{userId}")
    public List<Appointment> getAppointmentsByUser(@PathVariable Long userId) {
        return appointmentRepository.findByUserId(userId);
    }
}
