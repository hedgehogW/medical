package com.example.usermanagement.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.adviser.BaseResponse;
import com.example.usermanagement.service.DepartmentService;
import com.example.usermanagement.service.DoctorService;
import com.example.usermanagement.service.HospitalService;
import com.example.usermanagement.vi.DoctorResumePageVI;
import com.example.usermanagement.vi.PageRequest;
import com.example.usermanagement.vo.DepartmentResponse;
import com.example.usermanagement.vo.DoctorResumeVO;
import com.example.usermanagement.vo.HospitalResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wyz
 * @date 2024/10/12
 * @version 1.0.1
 * 修改选择医生的vi，vo
 */
@Api(tags = "获取医院信息")
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

    @ApiOperation(value = "科室列表查询", notes = "test")
    @PostMapping("/{hospitalId}/departments")
    public Page<DepartmentResponse> getDepartmentList(@PathVariable Long hospitalId,
                                                                          @RequestBody PageRequest departmentRequest){
        Page<DepartmentResponse> departmentList = departmentService.getDepartmentByHospitalId(hospitalId, departmentRequest);
        return departmentList;
    }

    @ApiOperation(value = "医生列表查询", notes = "test")
    @PostMapping("/{hospitalId}/departments/{departmentId}/doctor")
    public BaseResponse<Page<DoctorResumeVO>> getDoctorList(@RequestBody DoctorResumePageVI doctorResumePageVI){
        Page<DoctorResumeVO> doctorlist = doctorService.getDoctorByDepartmentId(doctorResumePageVI);
        return BaseResponse.success(doctorlist);
    }


}
