package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usermanagement.mapper.PatientMapper;
import com.example.usermanagement.model.Patient;
import com.example.usermanagement.service.PatientService;
import com.example.usermanagement.vo.PatientInformationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientMapper patientMapper;

    public PatientInformationVO getPatientInformation(Long id) {

        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("patient_id", id);
        Patient patient = patientMapper.selectOne(queryWrapper);

        PatientInformationVO patientInformationVO = new PatientInformationVO();
        BeanUtils.copyProperties(patient, patientInformationVO);

        return patientInformationVO;
    }
}
