package com.example.usermanagement.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.usermanagement.adviser.BaseResponse;
import com.example.usermanagement.dto.ApiResponse;
import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.dto.LoginResponse;
import com.example.usermanagement.model.UserInfo;
import com.example.usermanagement.service.AdminService;
import com.example.usermanagement.service.DoctorService;
import com.example.usermanagement.service.PatientService;
import com.example.usermanagement.service.SessionService;
import com.example.usermanagement.service.impl.UserServiceImpl;
import com.example.usermanagement.utils.JwtTokenProvider;
import com.example.usermanagement.vi.AdminCreateVI;
import com.example.usermanagement.vi.RegisterDoctorVI;
import com.example.usermanagement.vi.RegisterPatientVI;
import com.example.usermanagement.vo.AdminInformationVO;
import com.example.usermanagement.vo.DoctorInformationVO;
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
 * @date 2024/10/11
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
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private SessionService sessionService;


    /**
     * 患者注册
     */
    @ApiOperation(value = "患者注册")
    @PostMapping("/register/patient")
    public String registerPatient(@RequestBody RegisterPatientVI registerPatientVI) throws Exception {
        userService.registerPatient(registerPatientVI);
        return "Patient registered successfully.";
    }

    /**
     * 医生注册
     */
    @ApiOperation(value = "医生注册")
    @PostMapping("/register/doctor")
    public String registerDoctor(@RequestBody RegisterDoctorVI registerDoctorVI) throws Exception {
        userService.registerDoctor(registerDoctorVI);
        return "Doctor registered succeccfully.";
    }

//    @PostMapping("/register/admin")
//    public String registerAdmin(@RequestBody RegisterVI registerVI) throws Exception {
//        userService.registerAdmin(registerVI);
//        return "Admin registered successfully.";
//    }

    @ApiOperation(value = "创建管理员账户")
    @PostMapping("/create/admin")
    public BaseResponse<String> createAdmin(@RequestBody AdminCreateVI adminCreateVI) throws Exception {

        String user = sessionService.getDataFromSession("userinfo");
        JSONObject userJson = JSON.parseObject(user);
        Long userId =  (Long) userJson.get("id");

        adminService.createNormalAdmin(userId, adminCreateVI);
        return BaseResponse.success("create admin user successfully.");
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

    @ApiOperation(value = "获取患者个人信息")
    @GetMapping("/patient/userInformation")
    public BaseResponse<PatientInformationVO> getPatientInformation() {
        PatientInformationVO patientInformationVO = new PatientInformationVO();

        String user = sessionService.getDataFromSession("userinfo");
        JSONObject userJson = JSON.parseObject(user);
        Long userId =  (Long) userJson.get("id");
        patientInformationVO = patientService.getPatientInformation(userId);
        return BaseResponse.success(patientInformationVO);
    }

    @ApiOperation(value = "获取医生个人信息")
    @GetMapping("/doctor/userInformation")
    public BaseResponse<DoctorInformationVO> getDoctorInformation() {

        DoctorInformationVO doctorInformationVO = new DoctorInformationVO();

        String user = sessionService.getDataFromSession("userinfo");
        JSONObject userJson = JSON.parseObject(user);
        Long userId =  (Long) userJson.get("id");
        doctorInformationVO = doctorService.getDoctorInformation(userId);

        return BaseResponse.success(doctorInformationVO);
    }

    @ApiOperation(value = "获取管理员个人信息")
    @GetMapping("/admin/userInformation")
    public BaseResponse<AdminInformationVO> getAdminInformation() {

        AdminInformationVO adminInformationVO = new AdminInformationVO();

        String user = sessionService.getDataFromSession("userinfo");
        JSONObject userJson = JSON.parseObject(user);
        Long userId =  (Long) userJson.get("id");
        adminInformationVO = adminService.getAdminInformation(userId);

        return BaseResponse.success(adminInformationVO);
    }

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