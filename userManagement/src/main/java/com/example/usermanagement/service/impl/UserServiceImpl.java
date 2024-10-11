package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usermanagement.dto.RegisterRequest;
import com.example.usermanagement.mapper.PatientMapper;
import com.example.usermanagement.mapper.PermissionMapper;
import com.example.usermanagement.mapper.RoleMapper;
import com.example.usermanagement.mapper.UserMapper;
import com.example.usermanagement.model.Patient;
import com.example.usermanagement.model.Permission;
import com.example.usermanagement.model.UserInfo;
import com.example.usermanagement.service.UserService;
import com.example.usermanagement.vi.RegisterPatientVI;
import com.example.usermanagement.vi.RegisterVI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PatientMapper patientMapper;



    public List<Permission> findPermissionsByRoleId(Long roleId) {
        return permissionMapper.findPermissionsByRoleId(roleId);
    }

    /**
     * 用户注册
     */
    @Override
    public void register(RegisterVI registerVI) throws Exception {
        // 检查用户名是否存在
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", registerVI.getUsername());
        if (userMapper.selectOne(wrapper) != null) {
            throw new Exception("Username already exists.");
        }
        // 创建用户实体
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(registerVI.getUsername());
        userInfo.setPassword(passwordEncoder.encode(registerVI.getPassword()));
        userInfo.setIsEnable(true);
        LocalDateTime dateTime = LocalDateTime.now();
        userInfo.setCreatedAt(dateTime);
        userInfo.setUpdatedAt(dateTime);

        // 保存用户表
        userMapper.insert(userInfo);
    }

    @Override
    @Transactional
    public void registerPatient(RegisterPatientVI registerPatientVI) throws Exception {

        RegisterVI registerVI = new RegisterVI();
        registerVI.setUsername(registerPatientVI.getUsername());
        registerVI.setPassword(registerPatientVI.getPassword());
        register(registerVI);

        Patient patient = new Patient();
        UserInfo userInfo = findByUsername(registerPatientVI.getUsername());
        patient.setUserId(userInfo.getId());
//        patient.setPatientName(registerPatientVI.getPatientName());
//        patient.setPatientAge(registerPatientVI.getPatientAge());
//        patient.setEmail(registerPatientVI.getEmail());
//        patient.setHomeAddress(registerPatientVI.getHomeAddress());
//        patient.setPhoneNumber(registerPatientVI.getPhoneNumber());
//        patient.setTelephoneNumber(registerPatientVI.getTelephoneNumber());

        LocalDateTime dateTime = LocalDateTime.now();
        patient.setCreateTime(dateTime);
        patient.setUpdateTime(dateTime);
        patient.setState(true);

        //VO MODEL
        BeanUtils.copyProperties(registerPatientVI, patient);
        patientMapper.insert(patient);
    }

    /**
     * 根据用户名查找用户
     */
    public UserInfo findByUsername(String username) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        return userMapper.selectOne(wrapper);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(Long userId) {
        // 仅有管理员才能删除用户
    }
}