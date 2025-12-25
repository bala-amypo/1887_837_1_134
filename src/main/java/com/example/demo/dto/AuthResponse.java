package com.example.demo.dto;

import com.example.demo.model.UserRole;

public class AuthResponse {

    private String token;
    private String email;
    private UserRole role;

    public AuthResponse() {
    }

    public AuthResponse(String token, String email, UserRole role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
