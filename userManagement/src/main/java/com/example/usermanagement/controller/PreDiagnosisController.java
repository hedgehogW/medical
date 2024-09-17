package com.example.usermanagement.controller;

import com.example.usermanagement.model.PreDiagnosis;
import com.example.usermanagement.service.PreDiagnosisService;
import com.example.usermanagement.vi.PreDiagnosisRequest;
import com.example.usermanagement.vo.PreDiagnosisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PreDiagnosisController {

    @Autowired
    PreDiagnosisService preDiagnosisService;
    @PostMapping("/{patientId}/pre-diagnosis")
    public PreDiagnosisResponse submitPreDiagnosis(@PathVariable Long patientId, @RequestBody PreDiagnosisRequest preDiagnosisRequest) {
        PreDiagnosisResponse response;
        response = preDiagnosisService.submitPreDiagnosis(patientId, preDiagnosisRequest);
        return response;
    }

}
