package com.example.usermanagement.service;

import com.example.usermanagement.vi.PreDiagnosisRequest;
import com.example.usermanagement.vo.PreDiagnosisResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PreDiagnosisService {
    public PreDiagnosisResponse submitPreDiagnosis(Long patientId, PreDiagnosisRequest preDiagnosisRequest);

    public List<PreDiagnosisResponse> selectPreDiagnosis(Long doctorId,Long patientId);

    public PreDiagnosisResponse updatePreDiagnosis(PreDiagnosisRequest preDiagnosisRequest);
}
