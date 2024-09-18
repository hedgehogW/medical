package com.example.usermanagement.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.service.DepartmentService;
import com.example.usermanagement.service.DoctorService;
import com.example.usermanagement.service.HospitalService;
import com.example.usermanagement.service.impl.ExceptionService;
import com.example.usermanagement.utils.PageInfo;
import com.example.usermanagement.vi.PageRequest;
import com.example.usermanagement.vo.DepartmentResponse;
import com.example.usermanagement.vo.DoctorResponse;
import com.example.usermanagement.vo.HospitalResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "创建问诊")
@RestController
@RequestMapping("/hospital")
public class InquiryController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DoctorService doctorService;
    @ApiOperation(value = "医院列表查询", notes = "test")
    @PostMapping("/list")
    public Page<HospitalResponse> getHospitalList(@RequestBody PageRequest hospitalRequest) {
        Page<HospitalResponse> hospitalList = hospitalService.getHospitalList(hospitalRequest);
//        return ResponseEntity.ok(hospitalList);
        return hospitalList;
    }

    @PostMapping("/{hospitalId}/departments")
    public Page<DepartmentResponse> getDepartmentList(@PathVariable Long hospitalId,
                                                                          @RequestBody PageRequest departmentRequest){
        Page<DepartmentResponse> departmentList = departmentService.getDepartmentByHospitalId(hospitalId, departmentRequest);
        return departmentList;
    }

    @PostMapping("/{hospitalId}/departments/{departmentId}/doctor")
    public Page<DoctorResponse> getDoctorList(@PathVariable Long hospitalId,
                                                                  @PathVariable Long departmentId,
                                                                  @RequestBody PageRequest doctorRequest){
        Page<DoctorResponse> doctorlist = doctorService.getDoctorByDepartmentId(hospitalId, departmentId, doctorRequest);
        return doctorlist;
    }

//    @Autowired
//    private ExceptionService exceptionService;
//
//    @GetMapping("/exception")
//    public String ExcptionTest(@RequestParam int a){
//        if(a<0){
//            exceptionService.badRequest();
//        } else {
//            exceptionService.serverError();
//        }
//        return "hello";
//    }
}
