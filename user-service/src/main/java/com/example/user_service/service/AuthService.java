package com.example.user_service.service;

import com.example.user_service.dto.RegisterRequest;
import com.example.user_service.entity.Role;
import com.example.user_service.entity.User;
import com.example.user_service.repository.RoleRepository;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = roleRepository.save(new Role(null, "ROLE_USER"));
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setGender(request.getGender());
        user.setRoles(Set.of(userRole));

        userRepository.save(user);
    }
}
