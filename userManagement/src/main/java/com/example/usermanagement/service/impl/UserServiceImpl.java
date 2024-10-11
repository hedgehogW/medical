package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usermanagement.dto.RegisterRequest;
import com.example.usermanagement.mapper.*;
import com.example.usermanagement.model.*;
import com.example.usermanagement.service.UserService;
import com.example.usermanagement.vi.RegisterDoctorVI;
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

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


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

        // 保存user表
        userMapper.insert(userInfo);
    }

    @Override
    @Transactional
    public void registerPatient(RegisterPatientVI registerPatientVI) throws Exception {

        // 修改user表
        RegisterVI registerVI = new RegisterVI();
        BeanUtils.copyProperties(registerPatientVI, registerVI);
        register(registerVI);

        UserInfo userInfo = findByUsername(registerPatientVI.getUsername());

        // 修改user_role表
        UserRole userRole = new UserRole();
        userRole.setUserId(userInfo.getId());
        userRole.setRoleId(3L);
        userRoleMapper.insert(userRole);

        // 修改patient表
        Patient patient = new Patient();
        patient.setUserId(userInfo.getId());

        LocalDateTime dateTime = LocalDateTime.now();
        patient.setCreateTime(dateTime);
        patient.setUpdateTime(dateTime);
        patient.setStatus(true);

        // 复制patient到registerPatientVI
        BeanUtils.copyProperties(registerPatientVI, patient);

        patientMapper.insert(patient);
    }


    @Override
    @Transactional
    public void registerDoctor(RegisterDoctorVI registerDoctorVI) throws Exception {

        // user表
        RegisterVI registerVI = new RegisterVI();
        BeanUtils.copyProperties(registerDoctorVI, registerVI);
        register(registerVI);

        UserInfo userInfo = findByUsername(registerDoctorVI.getUsername());

        // 修改user_role表
        UserRole userRole = new UserRole();
        userRole.setUserId(userInfo.getId());
        userRole.setRoleId(2L);
        userRoleMapper.insert(userRole);

        // doctor 表
        Doctor doctor = new Doctor();
        doctor.setUserId(userInfo.getId());

        LocalDateTime dateTime = LocalDateTime.now();
        doctor.setCreateTime(dateTime);
        doctor.setUpdateTime(dateTime);
        doctor.setStatus(true);

        // 复制patient到registerPatientVI
        BeanUtils.copyProperties(registerDoctorVI, doctor);

        doctorMapper.insert(doctor);
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