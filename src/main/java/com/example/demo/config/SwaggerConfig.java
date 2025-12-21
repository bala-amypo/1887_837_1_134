package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Post-Surgery Recovery Tracker API")
                        .version("1.0.0")
                        .description("Healthcare API for monitoring post-surgical recovery"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
package com.example.demo.service;

import com.example.demo.model.DailySymptomLog;
import java.util.List;
import java.util.Optional;

public interface DailySymptomLogService {
    DailySymptomLog recordSymptomLog(DailySymptomLog log);
    List<DailySymptomLog> getLogsByPatient(Long patientId);
    Optional<DailySymptomLog> getLogById(Long id);
    DailySymptomLog updateSymptomLog(Long id, DailySymptomLog log);
}

