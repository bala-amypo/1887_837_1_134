package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ClinicalAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PatientProfile patient;

    private LocalDate alertDate;
    private String severity;
    private String message;
    private Boolean resolved;

    public ClinicalAlert() {}

    public ClinicalAlert(Long id, PatientProfile patient, LocalDate alertDate, String severity,
                         String message, Boolean resolved) {
        this.id = id;
        this.patient = patient;
        this.alertDate = alertDate;
        this.severity = severity;
        this.message = message;
        this.resolved = resolved;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PatientProfile getPatient() { return patient; }
    public void setPatient(PatientProfile patient) { this.patient = patient; }

    public LocalDate getAlertDate() { return alertDate; }
    public void setAlertDate(LocalDate alertDate) { this.alertDate = alertDate; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}
