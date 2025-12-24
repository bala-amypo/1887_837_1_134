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

    @PutMapping("/{id}/resolve")
    public ClinicalAlertRecord resolve(@PathVariable Long id) {
        return service.resolveAlert(id);
    }

    @GetMapping("/patient/{id}")
    public List<ClinicalAlertRecord> getByPatient(@PathVariable Long id) {
        return service.getAlertsByPatient(id);
    }
}
