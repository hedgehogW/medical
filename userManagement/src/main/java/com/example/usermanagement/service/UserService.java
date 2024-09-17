package com.example.usermanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.usermanagement.dto.RegisterRequest;
import com.example.usermanagement.model.UserInfo;

public interface UserService extends IService<UserInfo> {
    public void register(RegisterRequest registerRequest) throws Exception;
    public UserInfo findByUsername(String username);
    public void deleteUser(Long userId);

}
