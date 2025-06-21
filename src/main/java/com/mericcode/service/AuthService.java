package com.mericcode.service;

import com.mericcode.DTO.LoginRequest;
import com.mericcode.DTO.RegisterRequest;
import com.mericcode.jwt.JwtResponse;
import com.mericcode.jwt.JwtService;
import com.mericcode.user.Role;
import com.mericcode.user.User;
import com.mericcode.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        Role role = Role.ADAY;
        if (request.getRole() != null) {
            try {
                role = Role.valueOf(request.getRole());
            } catch (IllegalArgumentException var5) {
                return ResponseEntity.badRequest().body("Geçersiz rol: " + request.getRole());
            }
        }

        user.setRole(role);
        this.userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<?> login(LoginRequest request) {
        User user = (User)this.userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if(!this.passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        } else {
            String token = this.jwtService.generateToken(user.getEmail(),user.getRole().name());
            return ResponseEntity.ok(new JwtResponse(token));
        }
    }
}