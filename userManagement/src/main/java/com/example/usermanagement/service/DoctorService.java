package com.example.usermanagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.utils.PageInfo;
import com.example.usermanagement.vi.PageRequest;
import com.example.usermanagement.vo.DoctorResponse;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService {
    public Page<DoctorResponse> getDoctorByDepartmentId(Long hospitalId, Long departmentId, PageRequest doctorRequest);
}
