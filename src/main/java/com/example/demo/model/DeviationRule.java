package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "deviation_rules")
public class DeviationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String surgeryType;

    @NotBlank
    private String parameter;

    @NotNull
    @Positive
    private Integer threshold;

    @NotBlank
    private String severity;

    private Boolean active = true;

    public DeviationRule() {}

    public DeviationRule(String surgeryType, String parameter,
                         Integer threshold, String severity) {
        this.surgeryType = surgeryType;
        this.parameter = parameter;
        this.threshold = threshold;
        this.severity = severity;
        this.active = true;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getSurgeryType() { return surgeryType; }
    public void setSurgeryType(String surgeryType) { this.surgeryType = surgeryType; }
    public String getParameter() { return parameter; }
    public void setParameter(String parameter) { this.parameter = parameter; }
    public Integer getThreshold() { return threshold; }
    public void setThreshold(Integer threshold) { this.threshold = threshold; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
