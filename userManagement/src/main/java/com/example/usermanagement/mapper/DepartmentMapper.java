package com.example.usermanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanagement.model.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    @Select("SELECT * FROM department WHERE department_id IN " +
            "(SELECT department_id FROM hospital_dep WHERE hospital_id = #{hospitalId})")
    Page<Department> selectDepartmentsByHospitalId(Page<Department> page, @Param("hospitalId") Long hospitalId);
}