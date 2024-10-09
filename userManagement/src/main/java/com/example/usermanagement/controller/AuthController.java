package com.example.usermanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.usermanagement.adviser.BaseResponse;
import com.example.usermanagement.dto.*;
import com.example.usermanagement.model.UserInfo;
import com.example.usermanagement.service.SessionService;
import com.example.usermanagement.service.impl.UserServiceImpl;
import com.example.usermanagement.utils.JwtTokenProvider;
import com.example.usermanagement.vo.PatientInformationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * @author wyz
 * @date 2012/10/9
 * @version 2.0.0
 * 集成session保存登陆状态，获取个人信息
 */
@Api("用户管理和权限验证")
@RestController
@RequestMapping("/userManagement")
@SessionAttributes("userinfo")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private SessionService sessionService;

    /**
     * 用户注册
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest registerRequest) throws Exception {
        userService.register(registerRequest);
        return "User registered successfully.";
    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {

        // session 保存登陆状态
        UserInfo userInfo = userService.findByUsername(loginRequest.getUsername());
        String userJson = JSONObject.toJSONString(userInfo);
        if(userInfo != null)
            sessionService.storeDataInSession("userinfo", userJson);
        // 通过jwt生成token 保存登陆状态
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );
            String token = jwtTokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new ApiResponse("Invalid username or password."));
        }
    }

    /**
     * 获取当前用户信息
     */
//    @ApiOperation(value = "获取用户个人信息")
//    @GetMapping("/auth/me")
//    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return ResponseEntity.status(401).body(new ApiResponse("Unauthorized"));
//        }
//
//        UserInfo user = userService.findByUsername(authentication.getName());
//        if (user == null) {
//            return ResponseEntity.status(404).body(new ApiResponse("User not found."));
//        }
//
//        // 构建用户信息响应
//        UserInfoResponse userInfo = new UserInfoResponse(
//            user.getId(),
//            user.getUsername(),
//            user.getName(),
//            user.getPhoneNumber()
//        );
//
//        return ResponseEntity.ok(userInfo);
//    }

    @ApiOperation(value = "获取患者个人信息")
    @GetMapping("/patientUserInformation")
    public BaseResponse<PatientInformationVO> getPatientInformation() {
        PatientInformationVO patientInformationVO = new PatientInformationVO();
        String userJson = sessionService.getDataFromSession("userInfo");
        return BaseResponse.success(patientInformationVO);
    }
    @RequestMapping("/doctor")
    public String doctor(){
        return "doctor";
    }

    @RequestMapping("/patient")
    public String patient(){
        return "patient";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}