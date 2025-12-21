package com.example.demo.controller;

import com.example.demo.model.PatientProfile;
import com.example.demo.service.PatientProfileService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientProfileController {

    private final PatientProfileService service;

    // Constructor injection
    public PatientProfileController(PatientProfileService service) {
        this.service = service;
    }

    @PostMapping
    public PatientProfile createPatient(@Valid @RequestBody PatientProfile patient) {
        return service.createPatient(patient);
    }

    @GetMapping("/{id}")
    public PatientProfile getPatientById(@PathVariable Long id) {
        return service.getPatientById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @GetMapping
    public List<PatientProfile> getAllPatients() {
        return service.getAllPatients();
    }

    @PutMapping("/{id}/status")
    public PatientProfile updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return service.updatePatientStatus(id, active);
    }

    @GetMapping("/lookup/{patientId}")
    public PatientProfile getByPatientId(@PathVariable String patientId) {
        return service.findByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}
