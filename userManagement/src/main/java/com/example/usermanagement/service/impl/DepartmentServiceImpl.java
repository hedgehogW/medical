package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.mapper.DepartmentMapper;
import com.example.usermanagement.model.Department;
import com.example.usermanagement.service.DepartmentService;
import com.example.usermanagement.utils.PageInfo;
import com.example.usermanagement.vi.PageRequest;
import com.example.usermanagement.vo.DepartmentResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public Page<DepartmentResponse> getDepartmentByHospitalId(Long hospitalId, PageRequest pageRequest) {
        // 使用Page对象
        Page<Department> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());

        // 查询数据库，获取分页的Department数据
        Page<Department> departmentPage = departmentMapper.selectDepartmentsByHospitalId(page, hospitalId);

        // 转换为DTO
        List<DepartmentResponse> departmentDTOList = departmentPage.getRecords().stream()
                .map(department -> {
                    DepartmentResponse departmentResponse = new DepartmentResponse();
                    BeanUtils.copyProperties(department, departmentResponse);
                    return departmentResponse;
                }).collect(Collectors.toList());

        // 创建新的分页响应，包含DTO列表和分页信息
        Page<DepartmentResponse> responsePage = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize(), departmentPage.getTotal());
        responsePage.setRecords(departmentDTOList);

        return responsePage;
    }
}
