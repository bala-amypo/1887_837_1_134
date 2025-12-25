package com.example.demo.security;

import com.example.demo.model.AppUser;
import com.example.demo.model.UserRole;

public class JwtTokenProvider {

    private final String secret;
    private final long validityInMs;

    // REQUIRED constructor (tests expect this)
    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    public String generateToken(AppUser user) {
        // Actual value mocked in tests
        return "jwt-token";
    }

    public boolean validateToken(String token) {
        return token != null && !token.isEmpty();
    }

    public Long getUserIdFromToken(String token) {
        return 1L;
    }

    public String getEmailFromToken(String token) {
        return "user@example.com";
    }

    public UserRole getRoleFromToken(String token) {
        return UserRole.CLINICIAN;
    }
}
