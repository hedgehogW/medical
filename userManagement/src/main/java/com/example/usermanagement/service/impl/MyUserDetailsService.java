package com.example.usermanagement.service.impl;

import com.example.usermanagement.mapper.RoleMapper;
import com.example.usermanagement.mapper.UserMapper;
import com.example.usermanagement.model.Role;
import com.example.usermanagement.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper; // MyBatis-Plus接口

    @Autowired
    private RoleMapper roleMapper; // 角色查询


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 获取用户角色及权限
        List<Role> roles = roleMapper.findRolesByUserId(user.getId());

        // 将角色和权限转换为Spring Security的权限集合
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getIsEnable(),
                true,
                true,
                true,
                authorities
        );
    }
}
