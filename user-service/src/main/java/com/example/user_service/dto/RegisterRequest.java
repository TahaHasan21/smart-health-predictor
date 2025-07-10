package com.example.user_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String gender;
}
