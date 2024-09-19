package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.usermanagement.dto.RegisterRequest;
import com.example.usermanagement.mapper.PermissionMapper;
import com.example.usermanagement.mapper.RoleMapper;
import com.example.usermanagement.mapper.UserMapper;
import com.example.usermanagement.model.Permission;
import com.example.usermanagement.model.UserInfo;
import com.example.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> findPermissionsByRoleId(Long roleId) {
        return permissionMapper.findPermissionsByRoleId(roleId);
    }
    /**
     * 用户注册
     */
    public void register(RegisterRequest registerRequest) throws Exception {
        // 检查用户名是否存在
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", registerRequest.getUsername());
        if (userMapper.selectOne(wrapper) != null) {
            throw new Exception("Username already exists.");
        }
        // 创建用户实体
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(registerRequest.getUsername());
        userInfo.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userInfo.setName(registerRequest.getName());

        userInfo.setPhoneNumber(registerRequest.getPhoneNumber());
        userInfo.setIsEnable(true);
        LocalDateTime dateTime = LocalDateTime.now();
        userInfo.setCreatedAt(dateTime);
        userInfo.setUpdatedAt(dateTime);

        // 保存用户
        userMapper.insert(userInfo);
    }

    /**
     * 根据用户名查找用户
     */
    public UserInfo findByUsername(String username) {
        return this.lambdaQuery().eq(UserInfo::getUsername, username).one();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(Long userId) {
        // 仅有管理员才能删除用户
    }
}