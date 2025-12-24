package com.example.demo.repository;

import com.example.demo.model.DailySymptomLog;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryDailySymptomLogRepository
        implements DailySymptomLogRepository {

    private final Map<Long, DailySymptomLog> store = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public DailySymptomLog save(DailySymptomLog log) {
        if (log.getId() == null) {
            log.setId(idGen.getAndIncrement());
        }
        store.put(log.getId(), log);
        return log;
    }

    @Override
    public Optional<DailySymptomLog> findByPatientIdAndLogDate(
            Long patientId, LocalDate logDate) {

        return store.values().stream()
                .filter(l ->
                        Objects.equals(l.getPatientId(), patientId) &&
                        Objects.equals(l.getLogDate(), logDate))
                .findFirst();
    }

    @Override
    public List<DailySymptomLog> findByPatientId(Long patientId) {
        return store.values().stream()
                .filter(l -> Objects.equals(l.getPatientId(), patientId))
                .collect(Collectors.toList());
    }
}
