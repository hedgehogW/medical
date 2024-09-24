package com.example.usermanagement.controller;

import com.example.usermanagement.adviser.BaseResponse;
import com.example.usermanagement.service.PreDiagnosisService;
import com.example.usermanagement.utils.AliOSSUtils;
import com.example.usermanagement.vi.PreDiagnosisRequest;
import com.example.usermanagement.vo.PreDiagnosisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public BaseResponse<String> upload(MultipartFile file) throws IOException {
            //调用阿里云OSS工具类进行文件上传
            String url = aliOSSUtils.upload(file);
            return BaseResponse.success(url);
    }


}
