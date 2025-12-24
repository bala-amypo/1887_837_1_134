package com.example.demo.controller;

import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.service.ClinicalAlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alerts")
public class ClinicalAlertController {

    private final ClinicalAlertService service;

    public ClinicalAlertController(ClinicalAlertService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClinicalAlertRecord> getAll() {
        return service.getAllAlerts();
    }

    @GetMapping("/{id}")
    public Optional<ClinicalAlertRecord> getById(@PathVariable Long id) {
        return service.getAlertById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<ClinicalAlertRecord> getByPatient(@PathVariable Long patientId) {
        return service.getAlertsByPatient(patientId);
    }

    @PutMapping("/{id}/resolve")
    public ClinicalAlertRecord resolve(@PathVariable Long id) {
        return service.resolveAlert(id);
    }
}
