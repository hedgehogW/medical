package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.mapper.DoctorMapper;
import com.example.usermanagement.model.Department;
import com.example.usermanagement.model.Doctor;
import com.example.usermanagement.service.DoctorService;
import com.example.usermanagement.utils.PageInfo;
import com.example.usermanagement.vi.PageRequest;
import com.example.usermanagement.vo.DoctorResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public Page<DoctorResponse> getDoctorByDepartmentId(Long hospitalId, Long departmentId, PageRequest doctorRequest) {
        Page<Doctor> page = new Page<>(doctorRequest.getPageNum(), doctorRequest.getPageSize());
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hospital_id", hospitalId).eq("department_id", departmentId);
        IPage<Doctor> doctorIPage = doctorMapper.selectPage(page, queryWrapper);

        List<DoctorResponse> doctorResponsesList = doctorIPage.getRecords().stream()
                .map(doctor -> {
                    DoctorResponse doctorResponse = new DoctorResponse();
                    BeanUtils.copyProperties(doctor, doctorResponse);
                    return doctorResponse;
                }).collect(Collectors.toList());
        Page<DoctorResponse> doctorResponsePage = new Page<>(doctorRequest.getPageNum(),doctorRequest.getPageSize(),doctorIPage.getTotal());
        doctorResponsePage.setRecords(doctorResponsesList);

        return doctorResponsePage;
    }

    public Integer jij(){
        return 21;
    }
}
