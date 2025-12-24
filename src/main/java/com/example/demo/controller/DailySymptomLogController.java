package com.example.demo.controller;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/symptom-logs")
public class DailySymptomLogController {

    private final DailySymptomLogService service;

    public DailySymptomLogController(DailySymptomLogService service) {
        this.service = service;
    }

    @PostMapping
    public DailySymptomLog create(@RequestBody DailySymptomLog log) {
        return service.recordSymptomLog(log);
    }

    @GetMapping("/patient/{id}")
    public List<DailySymptomLog> getByPatient(@PathVariable Long id) {
        return service.getLogsByPatient(id);
    }
}
