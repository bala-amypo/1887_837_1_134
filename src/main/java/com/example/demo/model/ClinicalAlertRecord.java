package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clinical_alerts")
public class ClinicalAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alertMessage;

    private String severity;   // ✅ REQUIRED FIELD

    private LocalDate alertDate;

    private boolean resolved;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientProfile patient;

    // Constructors
    public ClinicalAlertRecord() {}

    public ClinicalAlertRecord(String alertMessage, String severity, LocalDate alertDate, boolean resolved) {
        this.alertMessage = alertMessage;
        this.severity = severity;
        this.alertDate = alertDate;
        this.resolved = resolved;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getAlertMessage() { return alertMessage; }
    public void setAlertMessage(String alertMessage) { this.alertMessage = alertMessage; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public LocalDate getAlertDate() { return alertDate; }
    public void setAlertDate(LocalDate alertDate) { this.alertDate = alertDate; }

    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }

    public PatientProfile getPatient() { return patient; }
    public void setPatient(PatientProfile patient) { this.patient = patient; }
}
