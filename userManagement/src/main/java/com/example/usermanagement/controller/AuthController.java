package com.example.usermanagement.controller;

import com.example.usermanagement.dto.*;
import com.example.usermanagement.model.UserInfo;
import com.example.usermanagement.service.impl.UserServiceImpl;
import com.example.usermanagement.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * @author wyz
 */

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * 用户注册
     */
    @PostMapping("/auth/register")
    public String registerUser(@RequestBody RegisterRequest registerRequest) throws Exception {
        userService.register(registerRequest);
        return "User registered successfully.";
    }
    /**
     * 用户登录
     */
    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
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
    @GetMapping("/auth/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(new ApiResponse("Unauthorized"));
        }

        UserInfo user = userService.findByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.status(404).body(new ApiResponse("User not found."));
        }

        // 构建用户信息响应
        UserInfoResponse userInfo = new UserInfoResponse(
            user.getId(),
            user.getUsername(),
            user.getName(),
            user.getPhoneNumber()
        );

        return ResponseEntity.ok(userInfo);
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