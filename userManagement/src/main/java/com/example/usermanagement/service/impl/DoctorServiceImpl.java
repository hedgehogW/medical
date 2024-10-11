package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.mapper.DepartmentMapper;
import com.example.usermanagement.mapper.DoctorMapper;
import com.example.usermanagement.mapper.HospitalMapper;
import com.example.usermanagement.model.Department;
import com.example.usermanagement.model.Doctor;
import com.example.usermanagement.model.Hospital;
import com.example.usermanagement.service.DoctorService;
import com.example.usermanagement.service.HospitalService;
import com.example.usermanagement.vi.DoctorResumePageVI;
import com.example.usermanagement.vi.PageRequest;
import com.example.usermanagement.vo.DoctorInformationVO;
import com.example.usermanagement.vo.DoctorResumeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public DoctorInformationVO getDoctorInformation(Long id) {

        DoctorInformationVO doctorInformationVO = new DoctorInformationVO();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", id);
        Doctor doctor = doctorMapper.selectOne(wrapper);

        BeanUtils.copyProperties(doctor, doctorInformationVO);

        Hospital hospital = hospitalMapper.selectById(doctor.getHospitalId());
        doctorInformationVO.setHospitalName(hospital.getHospitalName());
        Department department = departmentMapper.selectById(doctor.getDepartmentId());
        doctorInformationVO.setDepartmentName(department.getDepartmentName());

        return doctorInformationVO;
    }

    @Override
    public Page<DoctorResumeVO> getDoctorByDepartmentId(DoctorResumePageVI doctorResumePageVI) {
        Page<Doctor> page = new Page<>(doctorResumePageVI.getPageNum(), doctorResumePageVI.getPageSize());
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hospital_id", doctorResumePageVI.getHospitalId()).eq("department_id", doctorResumePageVI.getDepartmentId());
        IPage<Doctor> doctorIPage = doctorMapper.selectPage(page, queryWrapper);

        List<DoctorResumeVO> doctorResumeVOList = doctorIPage.getRecords().stream()
                .map(doctor -> {
                    DoctorResumeVO doctorResumeVO = new DoctorResumeVO();
                    BeanUtils.copyProperties(doctor, doctorResumeVO);
                    Hospital hospital = hospitalMapper.selectById(doctor.getHospitalId());
                    doctorResumeVO.setHospitalName(hospital.getHospitalName());
                    Department department = departmentMapper.selectById(doctor.getDepartmentId());
                    doctorResumeVO.setDepartmentName(department.getDepartmentName());

                    return doctorResumeVO;
                }).collect(Collectors.toList());
        Page<DoctorResumeVO> doctorResponsePage = new Page<>(doctorResumePageVI.getPageNum(), doctorResumePageVI.getPageSize(),doctorIPage.getTotal());
        doctorResponsePage.setRecords(doctorResumeVOList);

        return doctorResponsePage;
    }


}
