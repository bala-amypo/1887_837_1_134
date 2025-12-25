package com.example.demo.repository;

import com.example.demo.model.DailySymptomLog;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailySymptomLogRepository {

    DailySymptomLog save(DailySymptomLog log);

    Optional<DailySymptomLog> findById(Long id);

    Optional<DailySymptomLog> findByPatientIdAndLogDate(Long patientId, LocalDate logDate);

    List<DailySymptomLog> findByPatientId(Long patientId);
}
