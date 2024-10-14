package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usermanagement.mapper.PatientMapper;
import com.example.usermanagement.mapper.PreDiagnosisMapper;
import com.example.usermanagement.model.Patient;
import com.example.usermanagement.service.PatientService;
import com.example.usermanagement.vo.PatientInformationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientMapper patientMapper;

    @Autowired
    PreDiagnosisMapper preDiagnosisMapper;

    public PatientInformationVO getPatientInformation(Long id) {

        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        Patient patient = patientMapper.selectOne(queryWrapper);

        PatientInformationVO patientInformationVO = new PatientInformationVO();
        BeanUtils.copyProperties(patient, patientInformationVO);

        return patientInformationVO;
    }

    @Override
    public List<String> getPatientName(Long doctorId){
        List<Long> patientId= preDiagnosisMapper.findPatientIdByDoctorId(doctorId);
        HashSet<Long> set = new HashSet<>(patientId);
        List<Long> list = new ArrayList<>(set);
        List<String> name = new ArrayList<>();
        for (Long id:
             list) {
            name.add(patientMapper.selectById(id).getPatientName());
        }
        return name;
    }
}
