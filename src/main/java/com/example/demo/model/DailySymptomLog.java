package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class DailySymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PatientProfile patient;

    private LocalDate logDate;
    private Integer painLevel;
    private Integer mobilityLevel;
    private Integer fatigueLevel;
    private String notes;
    private LocalDateTime submittedAt;

    public DailySymptomLog() {}

    public DailySymptomLog(Long id, PatientProfile patient, LocalDate logDate, Integer painLevel,
                           Integer mobilityLevel, Integer fatigueLevel, String notes,
                           LocalDateTime submittedAt) {
        this.id = id;
        this.patient = patient;
        this.logDate = logDate;
        this.painLevel = painLevel;
        this.mobilityLevel = mobilityLevel;
        this.fatigueLevel = fatigueLevel;
        this.notes = notes;
        this.submittedAt = submittedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
}
