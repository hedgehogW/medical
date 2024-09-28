package com.example.usermanagement.service.impl;

import com.example.usermanagement.mapper.DoctorSeeDiagnosis;
import com.example.usermanagement.model.PreDiagnosis;
import com.example.usermanagement.service.DoctorSelectDiagnosis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorSelectDiagnosisServicempl implements DoctorSelectDiagnosis {
    @Autowired
    private DoctorSeeDiagnosis doctorSeeDiagnosis;
    @Override
    public List<PreDiagnosis> selectPreDiagnosis(Long patientId) {
        List<PreDiagnosis> list = doctorSeeDiagnosis.selectPreDiagnosis(patientId);
        return list;
    }
}
