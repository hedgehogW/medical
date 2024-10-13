package com.example.usermanagement.service;

import com.example.usermanagement.vi.PreDiagnosisRequest;
import com.example.usermanagement.vi.SubmitIn;
import com.example.usermanagement.vo.PreDiagnosisResponse;
import com.example.usermanagement.vo.SubmitOut;

import java.util.List;

public interface PreDiagnosisService {
    public PreDiagnosisResponse submitPreDiagnosis(Long patientId, PreDiagnosisRequest preDiagnosisRequest);

}
