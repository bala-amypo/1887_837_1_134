package com.example.demo.service.impl;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository logRepository;
    private final PatientProfileRepository patientRepository;

    public DailySymptomLogServiceImpl(DailySymptomLogRepository logRepository,
                                      PatientProfileRepository patientRepository) {
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public DailySymptomLog recordSymptomLog(DailySymptomLog log) {

        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("future date");
        }

        PatientProfile patient = patientRepository.findById(
                log.getPatient().getId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        if (logRepository.findByPatientIdAndLogDate(
                patient.getId(), log.getLogDate()).isPresent()) {
            throw new IllegalArgumentException("Duplicate log");
        }

        log.setPatient(patient);
        return logRepository.save(log);
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        return logRepository.findByPatientId(patientId);
    }

    @Override
    public Optional<DailySymptomLog> getLogById(Long id) {
        return logRepository.findById(id);
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog log) {
        DailySymptomLog existing = logRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found"));

        existing.setPainLevel(log.getPainLevel());
        existing.setMobilityLevel(log.getMobilityLevel());
        existing.setFatigueLevel(log.getFatigueLevel());
        existing.setAdditionalNotes(log.getAdditionalNotes());

        return logRepository.save(existing);
    }
}
