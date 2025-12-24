package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;
import com.example.demo.model.UserRole;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponse register(RegisterRequest request) {

        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(UserRole.USER)
                .build();

        AppUser saved = appUserRepository.save(user);

        String token = jwtTokenProvider.generateToken(saved.getEmail());

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        AppUser user = appUserRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtTokenProvider.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}
