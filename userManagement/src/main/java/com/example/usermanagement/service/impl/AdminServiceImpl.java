package com.example.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usermanagement.mapper.AdminMapper;
import com.example.usermanagement.model.Admin;
import com.example.usermanagement.service.AdminService;
import com.example.usermanagement.vo.AdminInformationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wyz
 * @date 2024/10/12
 * @version 1.0.0
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public AdminInformationVO getAdminInformation(Long id) {

        AdminInformationVO adminInformationVO = new AdminInformationVO();
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);

        Admin admin = adminMapper.selectOne(wrapper);
        BeanUtils.copyProperties(admin, adminInformationVO);
        return adminInformationVO;
    }
}
