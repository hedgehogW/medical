package com.example.usermanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.usermanagement.adviser.BaseResponse;
import com.example.usermanagement.model.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wyz
 * @date 2024/10/12
 * @version 1.0.0
 */

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}