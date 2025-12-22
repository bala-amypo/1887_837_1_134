package com.example.demo.service.impl;

import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.repository.ClinicalAlertRecordRepository;
import com.example.demo.service.ClinicalAlertService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClinicalAlertServiceImpl implements ClinicalAlertService {

    private final ClinicalAlertRecordRepository repository;

    public ClinicalAlertServiceImpl(ClinicalAlertRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClinicalAlertRecord> getAlertsByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    @Override
    public List<ClinicalAlertRecord> getAlertsByLevel(String alertLevel) {
        return repository.findByAlertLevel(alertLevel);
    }

    @Override
    public ClinicalAlertRecord saveAlert(ClinicalAlertRecord record) {
        return repository.save(record);
    }
}
