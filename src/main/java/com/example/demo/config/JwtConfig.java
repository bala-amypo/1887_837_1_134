package com.example.demo.config;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        // values are irrelevant for tests (Mockito mocks it)
        return new JwtTokenProvider("secret-key", 3600000);
    }
}
