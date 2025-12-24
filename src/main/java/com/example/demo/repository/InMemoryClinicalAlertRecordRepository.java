package com.example.demo.repository;

import com.example.demo.model.ClinicalAlertRecord;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryClinicalAlertRecordRepository
        implements ClinicalAlertRecordRepository {

    private final Map<Long, ClinicalAlertRecord> store = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public ClinicalAlertRecord save(ClinicalAlertRecord alert) {
        if (alert.getId() == null) {
            alert.setId(idGen.getAndIncrement());
        }
        store.put(alert.getId(), alert);
        return alert;
    }

    @Override
    public Optional<ClinicalAlertRecord> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<ClinicalAlertRecord> findByPatientId(Long patientId) {
        return store.values().stream()
                .filter(a -> Objects.equals(a.getPatientId(), patientId))
                .collect(Collectors.toList());
    }
}
