package com.example.usermanagement.controller;

import com.example.usermanagement.adviser.BaseResponse;
import com.example.usermanagement.service.DiagnosisService;
import com.example.usermanagement.service.PreDiagnosisService;
import com.example.usermanagement.utils.AliOSSUtils;
import com.example.usermanagement.vi.PreDiagnosisRequest;
import com.example.usermanagement.vi.SubmitIn;
import com.example.usermanagement.vo.PreDiagnosisResponse;
import com.example.usermanagement.vo.SubmitOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 在线问诊功能
 */
@Api(tags = "在线问诊")
@RestController
@RequestMapping("/patient")
public class PreDiagnosisController {

    @Autowired
    PreDiagnosisService preDiagnosisService;

    @Autowired
    private DiagnosisService diagnosisService;

    /**
     * 患者提交预诊
     * @param patientId
     * @param preDiagnosisRequest
     * @return
     */
    @ApiOperation(value = "患者提交预诊")
    @PostMapping("/{patientId}/pre-diagnosis")
    public PreDiagnosisResponse submitPreDiagnosis(@PathVariable Long patientId, @RequestBody PreDiagnosisRequest preDiagnosisRequest) {
        PreDiagnosisResponse response;
        response = preDiagnosisService.submitPreDiagnosis(patientId, preDiagnosisRequest);
        return response;
    }

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
            //调用阿里云OSS工具类进行文件上传
            String url = aliOSSUtils.upload(file);
            return url;
    }

    @PostMapping("/submit")
    @ApiOperation("上传病例")
    public BaseResponse<SubmitOut> submitResponse(@RequestBody SubmitIn in) {
        BaseResponse<SubmitOut> baseResponse = new BaseResponse<SubmitOut>();
        SubmitOut submitOut = diagnosisService.submitResponse(in);
        baseResponse.setData(submitOut);
        return baseResponse;
    }


}
