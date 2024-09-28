package com.example.usermanagement.controller;

import com.example.usermanagement.model.PreDiagnosis;
import com.example.usermanagement.service.DoctorSelectDiagnosis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctor1")
public class DoctorController {
    @Autowired
    private DoctorSelectDiagnosis doctorSelectDiagnosis;
    @GetMapping ("/diagnosis/{patinetId}")
    public List<PreDiagnosis> getDiagnosis(@PathVariable Long patinetId){

        List<PreDiagnosis> list = doctorSelectDiagnosis.selectPreDiagnosis(patinetId);
    return list;
    }
}
