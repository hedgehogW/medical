package com.example.usermanagement.controller;

import com.example.usermanagement.adviser.BaseResponse;
import com.example.usermanagement.service.DiagnosisService;
import com.example.usermanagement.service.PreDiagnosisService;
import com.example.usermanagement.utils.AliOSSUtils;
import com.example.usermanagement.vi.PreDiagnosisRequest;
import com.example.usermanagement.vi.SubmitIn;
import com.example.usermanagement.vo.OcrOut;
import com.example.usermanagement.vo.PreDiagnosisResponse;
import com.example.usermanagement.vo.SubmitOut;
import com.example.usermanagement.vo.UploadImageOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 在线问诊功能
 * @author wyz
 * @date 2024/10/14
 * @version 1.0.0
 */
@Api(tags = "在线问诊")
@RestController
@RequestMapping("/patient")
public class DiagnosisController {

    @Autowired
    PreDiagnosisService preDiagnosisService;

    @Autowired
    private DiagnosisService diagnosisService;


    @PostMapping("/submit")
    @ApiOperation("上传病例")
    public BaseResponse<SubmitOut> submitResponse(@RequestBody SubmitIn in) {
        BaseResponse<SubmitOut> baseResponse = new BaseResponse<SubmitOut>();
        SubmitOut submitOut = diagnosisService.submitResponse(in);
        baseResponse.setData(submitOut);
        return baseResponse;
    }

    @PostMapping("/uploadimage")
    @ApiOperation("上传图片")
    public BaseResponse<UploadImageOut> uploadResponse(@RequestBody MultipartFile file) {

        BaseResponse<UploadImageOut> baseResponse = new BaseResponse<UploadImageOut>();
        UploadImageOut uploadImageOut = diagnosisService.uploadResponse(file);
        baseResponse.setData(uploadImageOut);
        return baseResponse;
    }

    @PostMapping("/ocr")
    @ApiOperation("ocr图像文字识别")
    public BaseResponse<OcrOut> OcrResponse(@RequestBody MultipartFile file) throws TesseractException, IOException {

        BaseResponse<OcrOut> baseResponse = new BaseResponse<OcrOut>();
        OcrOut ocrOut = diagnosisService.OcrResponse(file);
        baseResponse.setData(ocrOut);
        return baseResponse;
    }

}
