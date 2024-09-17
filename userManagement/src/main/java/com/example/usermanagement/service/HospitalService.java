package com.example.usermanagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.utils.PageInfo;
import com.example.usermanagement.vi.PageRequest;
import com.example.usermanagement.vo.HospitalResponse;
import org.springframework.stereotype.Service;

@Service
public interface HospitalService {
    public Page<HospitalResponse> getHospitalList(PageRequest hospitalRequest);
}
