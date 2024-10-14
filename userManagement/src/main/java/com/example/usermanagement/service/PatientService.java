package com.example.usermanagement.service;

import com.example.usermanagement.vo.PatientInformationVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
     PatientInformationVO getPatientInformation(Long id);

     public List<String> getPatientName(Long doctorId);
}
