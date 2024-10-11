package com.example.usermanagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.vi.DoctorResumePageVI;
import com.example.usermanagement.vi.PageRequest;
import com.example.usermanagement.vo.DoctorInformationVO;
import com.example.usermanagement.vo.DoctorResumeVO;
import org.springframework.stereotype.Service;

/**
 * @author wyz
 * @date 2024/10/12
 * @version 2.0.0
 * 修改vi，vo
 */

@Service
public interface DoctorService {

    public Page<DoctorResumeVO> getDoctorByDepartmentId(DoctorResumePageVI doctorResumePageVI);

    public DoctorInformationVO getDoctorInformation(Long id);
}
