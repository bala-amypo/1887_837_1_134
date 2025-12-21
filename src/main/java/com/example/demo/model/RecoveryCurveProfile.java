package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "recovery_curve_profiles")
public class RecoveryCurveProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String surgeryType;

    @NotNull
    @Min(0)
    private Integer dayNumber;

    @NotNull @Min(0) @Max(10)
    private Integer expectedPainLevel;

    @NotNull @Min(0) @Max(10)
    private Integer expectedMobilityLevel;

    @NotNull @Min(0) @Max(10)
    private Integer expectedFatigueLevel;

    public RecoveryCurveProfile() {}

    public RecoveryCurveProfile(String surgeryType, Integer dayNumber,
                                Integer expectedPainLevel,
                                Integer expectedMobilityLevel,
                                Integer expectedFatigueLevel) {
        this.surgeryType = surgeryType;
        this.dayNumber = dayNumber;
        this.expectedPainLevel = expectedPainLevel;
        this.expectedMobilityLevel = expectedMobilityLevel;
        this.expectedFatigueLevel = expectedFatigueLevel;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getSurgeryType() { return surgeryType; }
    public void setSurgeryType(String surgeryType) { this.surgeryType = surgeryType; }
    public Integer getDayNumber() { return dayNumber; }
    public void setDayNumber(Integer dayNumber) { this.dayNumber = dayNumber; }
    public Integer getExpectedPainLevel() { return expectedPainLevel; }
    public void setExpectedPainLevel(Integer expectedPainLevel) { this.expectedPainLevel = expectedPainLevel; }
    public Integer getExpectedMobilityLevel() { return expectedMobilityLevel; }
    public void setExpectedMobilityLevel(Integer expectedMobilityLevel) { this.expectedMobilityLevel = expectedMobilityLevel; }
    public Integer getExpectedFatigueLevel() { return expectedFatigueLevel; }
    public void setExpectedFatigueLevel(Integer expectedFatigueLevel) { this.expectedFatigueLevel = expectedFatigueLevel; }
}
