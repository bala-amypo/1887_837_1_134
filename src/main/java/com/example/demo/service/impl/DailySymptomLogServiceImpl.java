package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DailySymptomLog;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository repository;
    private final PatientProfileRepository patientRepository;

    public DailySymptomLogServiceImpl(
            DailySymptomLogRepository repository,
            PatientProfileRepository patientRepository) {
        this.repository = repository;
        this.patientRepository = patientRepository;
    }

    // ✅ CREATE
    @Override
    public DailySymptomLog create(DailySymptomLog log) {

        Long patientId = log.getPatient().getId();

        PatientProfile patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with id " + patientId)
                );

        log.setPatient(patient);
        return repository.save(log);
    }

    // ✅ UPDATE
    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog log) {

        DailySymptomLog existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Symptom log not found with id " + id)
                );

        existing.setPainLevel(log.getPainLevel());
        existing.setMobilityLevel(log.getMobilityLevel());
        existing.setFatigueLevel(log.getFatigueLevel());
        existing.setAdditionalNotes(log.getAdditionalNotes());

        return repository.save(existing);
    }

    // ✅ DELETE
    @Override
    public void deleteSymptomLog(Long id) {
        DailySymptomLog log = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Symptom log not found with id " + id)
                );
        repository.delete(log);
    }

    // ✅ READ
    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}
