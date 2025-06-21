package com.mericcode.DTO;

import com.mericcode.user.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String password;
    private String role;
}
