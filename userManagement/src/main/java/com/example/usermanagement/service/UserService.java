package com.example.usermanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.usermanagement.dto.RegisterRequest;
import com.example.usermanagement.model.UserInfo;
import com.example.usermanagement.vi.RegisterDoctorVI;
import com.example.usermanagement.vi.RegisterPatientVI;
import com.example.usermanagement.vi.RegisterVI;
import com.example.usermanagement.vo.PatientInformationVO;

public interface UserService {
    public void register(RegisterVI registerVI) throws Exception;

    public UserInfo findByUsername(String username);

    public void deleteUser(Long userId);

    public void registerPatient(RegisterPatientVI registerPatientVI) throws Exception;

    public void registerDoctor(RegisterDoctorVI registerDoctorVI) throws Exception;

}
