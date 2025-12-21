package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "clinical_alert_records")
public class ClinicalAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull
    @JsonIgnoreProperties({"symptomLogs", "alerts"})
    private PatientProfile patient;

    @NotNull
    private Long logId;

    @NotBlank
    private String alertType;

    @NotBlank
    private String severity;

    @NotBlank
    @Lob
    private String message;

    private Boolean resolved = false;
    private LocalDate alertDate = LocalDate.now();

    public ClinicalAlertRecord() {}

    public ClinicalAlertRecord(PatientProfile patient, Long logId,
                               String alertType, String severity,
                               String message) {
        this.patient = patient;
        this.logId = logId;
        this.alertType = alertType;
        this.severity = severity;
        this.message = message;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public PatientProfile getPatient() { return patient; }
    public void setPatient(PatientProfile patient) { this.patient = patient; }
    public Long getLogId() { return logId; }
    public void setLogId(Long logId) { this.logId = logId; }
    public String getAlertType() { return alertType; }
    public void setAlertType(String alertType) { this.alertType = alertType; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}
