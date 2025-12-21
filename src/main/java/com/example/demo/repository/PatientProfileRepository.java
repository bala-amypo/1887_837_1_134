package com.example.demo.repository;

import com.example.demo.model.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {

    Optional<PatientProfile> findByEmail(String email);

    Optional<PatientProfile> findByPatientId(String patientId);

    List<PatientProfile> findByActive(Boolean active);

    List<PatientProfile> findBySurgeryType(String surgeryType);
}
