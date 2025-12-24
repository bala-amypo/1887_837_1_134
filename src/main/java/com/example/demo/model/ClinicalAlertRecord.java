package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalAlertRecord {

    private Long id;
    private Long patientId;
    private Long logId;
    private String severity;
    private Boolean resolved;
}
