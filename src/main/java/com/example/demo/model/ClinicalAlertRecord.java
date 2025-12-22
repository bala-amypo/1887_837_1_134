package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ClinicalAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private String alertMessage;

    private String alertLevel; // use this instead of severity

    // getters and setters
}
