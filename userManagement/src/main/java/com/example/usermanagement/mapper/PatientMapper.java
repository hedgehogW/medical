package com.example.usermanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.usermanagement.model.Patient;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wyz
 * @date 2024/10/9
 * @version 1.0.0
 * 患者mapper创建
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}