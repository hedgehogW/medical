package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usermanagement.mapper.AdminMapper;
import com.example.usermanagement.mapper.UserMapper;
import com.example.usermanagement.mapper.UserRoleMapper;
import com.example.usermanagement.model.Admin;
import com.example.usermanagement.model.Patient;
import com.example.usermanagement.model.UserInfo;
import com.example.usermanagement.model.UserRole;
import com.example.usermanagement.service.AdminService;
import com.example.usermanagement.vi.AdminCreateVI;
import com.example.usermanagement.vi.RegisterVI;
import com.example.usermanagement.vo.AdminInformationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author wyz
 * @date 2024/10/12
 * @version 1.0.0
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public AdminInformationVO getAdminInformation(Long id) {

        AdminInformationVO adminInformationVO = new AdminInformationVO();
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);

        Admin admin = adminMapper.selectOne(wrapper);
        BeanUtils.copyProperties(admin, adminInformationVO);
        if(admin.getSuperAdmin() == true) {
            adminInformationVO.setSuperAdminString("超级管理员");
        } else {
            adminInformationVO.setSuperAdminString("普通管理员");
        }
        return adminInformationVO;
    }

    @Override
    public void createNormalAdmin(Long id, AdminCreateVI adminCreateVI) throws Exception {

        // 检查是否为superroot
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", id);
        UserRole userRole = userRoleMapper.selectOne(wrapper);
        if (userRole.getRoleId() == 1) {
            Admin admin = adminMapper.selectOne(wrapper);
            if (admin.getSuperAdmin() != true) {
                throw new Exception("no authentic");
            }
        } else {
            throw new Exception("no authentic");
        }
        // 修改user表
        RegisterVI registerVI = new RegisterVI();
        BeanUtils.copyProperties(adminCreateVI, registerVI);
        // 检查用户名是否存在
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("username", registerVI.getUsername());
        if (userMapper.selectOne(wrapper1) != null) {
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

        UserInfo userInfo2 = userMapper.selectOne(wrapper1);

        // 修改user_role表
        UserRole userRole1 = new UserRole();
        userRole1.setUserId(userInfo2.getId());
        userRole1.setRoleId(1L);
        userRoleMapper.insert(userRole1);

        // admin 表
        Admin admin = new Admin();
        admin.setUserId(userInfo2.getId());

        LocalDateTime dateTime2 = LocalDateTime.now();
        admin.setCreateTime(dateTime2);
        admin.setUpdateTime(dateTime2);
        admin.setStatus(true);

        // 复制adminCreateVI到admin
        BeanUtils.copyProperties(adminCreateVI, admin);

        adminMapper.insert(admin);
    }
}
