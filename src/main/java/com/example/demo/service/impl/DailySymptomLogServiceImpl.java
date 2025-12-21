package com.example.demo.service.impl;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

        log.setPatient(patient); // 🔥 THIS LINE IS THE KEY

        return repository.save(log);
    }
}
