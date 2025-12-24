package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailySymptomLog {

    private Long id;
    private Long patientId;
    private LocalDate logDate;

    private Integer painLevel;      // ✅ change
    private Integer mobilityLevel;  // ✅ change
    private Integer fatigueLevel;   // ✅ change

    private String additionalNotes;
}
