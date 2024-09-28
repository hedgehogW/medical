package com.example.usermanagement.service.impl;

import com.example.usermanagement.mapper.PreDiagnosisImagesMapper;
import com.example.usermanagement.mapper.PreDiagnosisMapper;
import com.example.usermanagement.model.PreDiagnosis;
import com.example.usermanagement.service.PreDiagnosisService;
import com.example.usermanagement.vi.PreDiagnosisRequest;
import com.example.usermanagement.vo.PreDiagnosisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PreDiagnosisServiceImpl implements PreDiagnosisService {

    @Autowired
    PreDiagnosisMapper preDiagnosisMapper;

    @Autowired
    PreDiagnosisImagesMapper preDiagnosisImagesMapper;
    @Override
    public PreDiagnosisResponse submitPreDiagnosis(Long patientId, PreDiagnosisRequest preDiagnosisRequest) {
        // 创建预诊记录实体
        PreDiagnosis preDiagnosis = new PreDiagnosis();
        preDiagnosis.setPatientId(patientId);
        preDiagnosis.setDoctorId(preDiagnosisRequest.getDoctorId());
        preDiagnosis.setRichText(preDiagnosisRequest.getRichText());
        preDiagnosis.setSubmitTime(LocalDateTime.now());

        // 保存预诊记录
        preDiagnosisMapper.insert(preDiagnosis);

//        // 保存图片URL
//        for (String imageUrl : preDiagnosisRequest.getImageUrls()) {
//            PreDiagnosisImages preDiagnosisImage = new PreDiagnosisImages();
//            preDiagnosisImage.setPreDiagnosisId(preDiagnosis.getId());
//            preDiagnosisImage.setImageUrl(imageUrl);
//            preDiagnosisImagesMapper.insert(preDiagnosisImage);
//        }

        // 构建返回对象
        PreDiagnosisResponse response = new PreDiagnosisResponse();
        response.setPreDiagnosisId(preDiagnosis.getId());
        response.setPatientId(preDiagnosis.getPatientId());
        response.setDoctorId(preDiagnosis.getDoctorId());
        response.setRichText(preDiagnosis.getRichText());
        response.setImageUrls(preDiagnosisRequest.getImageUrls());
        response.setSubmitTime(preDiagnosis.getSubmitTime());

        return response;
    }
}
