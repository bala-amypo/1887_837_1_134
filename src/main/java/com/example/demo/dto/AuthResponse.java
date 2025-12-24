package com.example.demo.dto;

import com.example.demo.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String email;
    private UserRole role;
    private Long userId;
}
