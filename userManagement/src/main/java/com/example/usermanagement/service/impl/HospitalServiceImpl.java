package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.mapper.HospitalMapper;
import com.example.usermanagement.model.Hospital;
import com.example.usermanagement.service.HospitalService;
import com.example.usermanagement.utils.PageInfo;
import com.example.usermanagement.vi.PageRequest;
import com.example.usermanagement.vo.HospitalResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    HospitalMapper hospitalMapper;

    @Override
    public Page<HospitalResponse> getHospitalList(PageRequest pageRequest) {
        Page<Hospital> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());

        IPage<Hospital> hospitalPage = hospitalMapper.selectPage(page, null);

        // Convert to DTO
        List<HospitalResponse> hospitalDTOList = hospitalPage.getRecords().stream()
                .map(hospital -> {
                    HospitalResponse hospitalResponse = new HospitalResponse();
                    BeanUtils.copyProperties(hospital, hospitalResponse);
                    return hospitalResponse;
                }).collect(Collectors.toList());

        Page<HospitalResponse> hospitalResponsePage = new Page<>(pageRequest.getPageNum(),pageRequest.getPageSize(),hospitalPage.getTotal());

        hospitalResponsePage.setRecords(hospitalDTOList);

        return hospitalResponsePage;
        }

}

