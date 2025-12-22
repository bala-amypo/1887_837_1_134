package com.example.demo.service;

import com.example.demo.model.ClinicalAlertRecord;
import java.util.List;

public interface ClinicalAlertService {

    ClinicalAlertRecord saveAlert(ClinicalAlertRecord record);

    List<ClinicalAlertRecord> getAlertsByPatientId(Long patientId);

    List<ClinicalAlertRecord> getAlertsByLevel(String alertLevel);
}
