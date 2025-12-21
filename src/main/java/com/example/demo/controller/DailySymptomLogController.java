package com.example.demo.controller;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.service.DailySymptomLogService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/symptom-logs")
@CrossOrigin
public class DailySymptomLogController {

    private final DailySymptomLogService service;

    public DailySymptomLogController(DailySymptomLogService service) {
        this.service = service;
    }

    // ✅ CREATE
    @PostMapping
    public DailySymptomLog create(@Valid @RequestBody DailySymptomLog log) {
        return service.create(log);
    }

    // ✅ READ (by patient)
    @GetMapping("/patient/{patientId}")
    public List<DailySymptomLog> getByPatient(@PathVariable Long patientId) {
        return service.getLogsByPatient(patientId);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public DailySymptomLog update(
            @PathVariable Long id,
            @Valid @RequestBody DailySymptomLog log) {
        return service.updateSymptomLog(id, log);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteSymptomLog(id);
    }
}
