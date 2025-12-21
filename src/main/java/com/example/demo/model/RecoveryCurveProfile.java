package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "recovery_curve_profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecoveryCurveProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String surgeryType;

    @NotNull
    @Min(0)
    private Integer dayNumber;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer expectedPainLevel;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer expectedMobilityLevel;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer expectedFatigueLevel;
}
