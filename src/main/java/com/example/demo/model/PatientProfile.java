package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patient_profiles")
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String patientId;

    @NotBlank
    private String fullName;

    @NotNull
    @Positive
    private Integer age;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String surgeryType;

    private Boolean active = true;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DailySymptomLog> symptomLogs;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClinicalAlertRecord> alerts;

    // No-arg constructor
    public PatientProfile() {
        this.createdAt = LocalDateTime.now();
    }

    // Parameterized constructor
    public PatientProfile(String patientId, String fullName, Integer age,
                          String email, String surgeryType) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.surgeryType = surgeryType;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSurgeryType() { return surgeryType; }
    public void setSurgeryType(String surgeryType) { this.surgeryType = surgeryType; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public List<DailySymptomLog> getSymptomLogs() { return symptomLogs; }
    public void setSymptomLogs(List<DailySymptomLog> symptomLogs) { this.symptomLogs = symptomLogs; }

    public List<ClinicalAlertRecord> getAlerts() { return alerts; }
    public void setAlerts(List<ClinicalAlertRecord> alerts) { this.alerts = alerts; }
}
