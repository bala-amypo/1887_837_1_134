package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_profiles",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "patientId"),
           @UniqueConstraint(columnNames = "email")
       })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String patientId;

    @NotBlank
    private String fullName;

    @NotNull
    @Positive
    private Integer age;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String surgeryType;

    private Boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
