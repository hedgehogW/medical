package com.example.usermanagement.controller;

import com.example.usermanagement.service.PatientService;
import com.example.usermanagement.service.PreDiagnosisService;
import com.example.usermanagement.vo.PreDiagnosisResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="医生端")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private PreDiagnosisService preDiagnosisService;

    @Autowired
    private PatientService patientService;

    @ApiOperation(value = "查询医生名下的病人")
    @PostMapping("/{doctorId}")
    List<String> getPatientName(@PathVariable Long doctorId){
        return patientService.getPatientName(doctorId);
    }

    @ApiOperation(value = "医生对应病历查询",tags = "test")
    @PostMapping("/preDiagnosis/{patientId}")
    List<PreDiagnosisResponse> getPatientPreDiagnosis(@PathVariable Long patientId){
        List<PreDiagnosisResponse> list =preDiagnosisService.selectPreDiagnosis(1L,patientId);
        return list;
    }
}