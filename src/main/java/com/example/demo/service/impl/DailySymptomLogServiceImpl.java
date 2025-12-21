package com.example.demo.service.impl;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository; // 🔥 REQUIRED
import com.example.demo.service.DailySymptomLogService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

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
}
