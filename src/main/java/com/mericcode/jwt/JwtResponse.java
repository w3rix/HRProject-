package com.mericcode.jwt;

import com.mericcode.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
