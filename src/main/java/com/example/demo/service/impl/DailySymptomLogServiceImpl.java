package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;

import java.time.temporal.ChronoUnit;
import java.util.List;

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

        logRepository.findByPatientIdAndLogDate(
                log.getPatientId(), log.getLogDate())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Duplicate log");
                });

        DailySymptomLog saved = logRepository.save(log);

        long day = ChronoUnit.DAYS.between(
                patient.getCreatedAt().toLocalDate(),
                log.getLogDate());

        recoveryCurveService.getCurveForSurgery(patient.getSurgeryType());
        deviationRuleService.getActiveRules();

        return saved;
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {

        patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        return logRepository.findByPatientId(patientId);
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long logId, DailySymptomLog updated) {

        DailySymptomLog existing = logRepository.findById(logId)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        patientRepository.findById(updated.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        existing.setPainLevel(updated.getPainLevel());
        existing.setMobilityLevel(updated.getMobilityLevel());
        existing.setFatigueLevel(updated.getFatigueLevel());
        existing.setAdditionalNotes(updated.getAdditionalNotes());

        return logRepository.save(existing);
    }
}
