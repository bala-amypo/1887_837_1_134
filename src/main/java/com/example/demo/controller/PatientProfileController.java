package com.example.demo.controller;

import com.example.demo.model.PatientProfile;
import com.example.demo.service.PatientProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientProfileController {

    private final PatientProfileService service;

    public PatientProfileController(PatientProfileService service) {
        this.service = service;
    }

    @PostMapping
    public PatientProfile create(@RequestBody PatientProfile profile) {
        return service.createPatient(profile);
    }

    @GetMapping("/{id}")
    public PatientProfile getById(@PathVariable Long id) {
        return service.getPatientById(id);
    }

    @GetMapping
    public List<PatientProfile> getAll() {
        return service.getAllPatients();
    }

    @GetMapping("/search/{patientId}")
    public Optional<PatientProfile> findByPatientId(@PathVariable String patientId) {
        return service.findByPatientId(patientId);
    }

    @PutMapping("/{id}/status")
    public PatientProfile updateStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {
        return service.updatePatientStatus(id, active);
    }
}
