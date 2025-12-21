package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "daily_symptom_logs",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"patient_id", "logDate"})
    }
)
public class DailySymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull
    private PatientProfile patient;

    @NotNull
    private LocalDate logDate;

    @NotNull @Min(0) @Max(10)
    private Integer painLevel;

    @NotNull @Min(0) @Max(10)
    private Integer mobilityLevel;

    @NotNull @Min(0) @Max(10)
    private Integer fatigueLevel;

    @Size(max = 2000)
    @Lob
    private String additionalNotes;

    private LocalDateTime submittedAt;

    public DailySymptomLog() {
        this.submittedAt = LocalDateTime.now();
    }

    public DailySymptomLog(PatientProfile patient, LocalDate logDate,
                           Integer painLevel, Integer mobilityLevel,
                           Integer fatigueLevel) {
        this.patient = patient;
        this.logDate = logDate;
        this.painLevel = painLevel;
        this.mobilityLevel = mobilityLevel;
        this.fatigueLevel = fatigueLevel;
        this.submittedAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public PatientProfile getPatient() { return patient; }
    public void setPatient(PatientProfile patient) { this.patient = patient; }
    public LocalDate getLogDate() { return logDate; }
    public void setLogDate(LocalDate logDate) { this.logDate = logDate; }
    public Integer getPainLevel() { return painLevel; }
    public void setPainLevel(Integer painLevel) { this.painLevel = painLevel; }
    public Integer getMobilityLevel() { return mobilityLevel; }
    public void setMobilityLevel(Integer mobilityLevel) { this.mobilityLevel = mobilityLevel; }
    public Integer getFatigueLevel() { return fatigueLevel; }
    public void setFatigueLevel(Integer fatigueLevel) { this.fatigueLevel = fatigueLevel; }
    public String getAdditionalNotes() { return additionalNotes; }
    public void setAdditionalNotes(String additionalNotes) { this.additionalNotes = additionalNotes; }
}
