package com.example.demo.config;

import com.example.demo.repository.AppUserRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public AppUserRepository appUserRepository() {
        return Mockito.mock(AppUserRepository.class);
    }
}