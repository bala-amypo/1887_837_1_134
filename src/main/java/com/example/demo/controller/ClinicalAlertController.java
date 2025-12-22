package com.example.demo.controller;

import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.service.ClinicalAlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class ClinicalAlertController {

    private final ClinicalAlertService service;

    public ClinicalAlertController(ClinicalAlertService service) {
        this.service = service;
    }

    @PostMapping
    public ClinicalAlertRecord createAlert(@RequestBody ClinicalAlertRecord record) {
        return service.saveAlert(record);
    }

    @GetMapping("/patient/{patientId}")
    public List<ClinicalAlertRecord> getByPatient(@PathVariable Long patientId) {
        return service.getAlertsByPatientId(patientId);
    }

    @GetMapping("/level/{level}")
    public List<ClinicalAlertRecord> getByLevel(@PathVariable String level) {
        return service.getAlertsByLevel(level);
    }
}
