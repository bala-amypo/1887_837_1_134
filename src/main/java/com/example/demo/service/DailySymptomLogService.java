package com.example.demo.service;

import com.example.demo.model.DailySymptomLog;

import java.util.List;
import java.util.Optional;

public interface DailySymptomLogService {

    DailySymptomLog create(DailySymptomLog log);

    DailySymptomLog updateSymptomLog(Long id, DailySymptomLog log);

    void deleteSymptomLog(Long id);

    List<DailySymptomLog> getLogsByPatient(Long patientId);
}
