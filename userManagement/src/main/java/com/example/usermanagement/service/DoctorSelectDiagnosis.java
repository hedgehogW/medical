package com.example.usermanagement.service;

import com.example.usermanagement.model.PreDiagnosis;

import java.util.List;

public interface DoctorSelectDiagnosis {
    public List<PreDiagnosis> selectPreDiagnosis(Long patientId);
}
