package com.example.usermanagement.service;

import com.example.usermanagement.vo.AdminInformationVO;

/**
 * @author wyz
 * @date 2024/10/12
 * @version 1.0.0
 */

public interface AdminService {

    public AdminInformationVO getAdminInformation(Long id);
}
