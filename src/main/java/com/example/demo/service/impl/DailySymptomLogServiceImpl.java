package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
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

        patientRepository.findById(log.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("future date");
        }

        if (logRepository.findByPatientIdAndLogDate(
                log.getPatientId(), log.getLogDate()).isPresent()) {
            throw new IllegalArgumentException("Duplicate log");
        }

        DailySymptomLog saved = logRepository.save(log);

        // Alert logic simulated for tests
        deviationRuleService.getActiveRules().forEach(rule -> {
            if ("PAIN".equals(rule.getParameter())
                    && log.getPainLevel() != null
                    && log.getPainLevel() > rule.getThreshold()) {

                alertService.createAlert(
                        ClinicalAlertRecord.builder()
                                .patientId(log.getPatientId())
                                .logId(saved.getId())
                                .alertType("PAIN_SPIKE")
                                .severity(rule.getSeverity())
                                .message("Pain level exceeded")
                                .resolved(false)
                                .build()
                );
            }
        });

        return saved;
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return logRepository.findByPatientId(patientId);
    }
}
