package com.example.usermanagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.utils.PageInfo;
import com.example.usermanagement.vi.PageRequest;
import com.example.usermanagement.vo.DepartmentResponse;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    public Page<DepartmentResponse> getDepartmentByHospitalId(Long hospitalId, PageRequest departmentRequest);
}
