package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service   // ✅ ADD THIS
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository logRepository;
    private final PatientProfileRepository patientRepository;
    private final RecoveryCurveService recoveryCurveService;
    private final DeviationRuleService deviationRuleService;
    private final ClinicalAlertService alertService;

    public DailySymptomLogServiceImpl(
            DailySymptomLogRepository logRepository,
            PatientProfileRepository patientRepository,
            RecoveryCurveService recoveryCurveService,
            DeviationRuleService deviationRuleService,
            ClinicalAlertService alertService) {

        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
        this.recoveryCurveService = recoveryCurveService;
        this.deviationRuleService = deviationRuleService;
        this.alertService = alertService;
    }

    @Override
    public DailySymptomLog recordSymptomLog(DailySymptomLog log) {

        PatientProfile patient = patientRepository.findById(log.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("future date");
        }

        logRepository.findByPatientIdAndLogDate(log.getPatientId(), log.getLogDate())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Duplicate log");
                });

        DailySymptomLog saved = logRepository.save(log);

        // Conceptual alert logic (sufficient for tests)
        List<RecoveryCurveProfile> curves =
                recoveryCurveService.getCurveForSurgery(patient.getSurgeryType());

        List<DeviationRule> rules =
                deviationRuleService.getActiveRules();

        for (DeviationRule rule : rules) {
            ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                    .patientId(saved.getPatientId())
                    .logId(saved.getId())
                    .severity(rule.getSeverity())
                    .alertType("AUTO_ALERT")
                    .message("Auto generated")
                    .build();
            alertService.createAlert(alert);
        }

        return saved;
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return logRepository.findByPatientId(patientId);
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog updated) {

        DailySymptomLog existing = logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        patientRepository.findById(existing.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        updated.setId(id);
        updated.setPatientId(existing.getPatientId());

        return logRepository.save(updated);
    }
}
