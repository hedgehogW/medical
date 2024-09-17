package com.example.usermanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.usermanagement.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("SELECT p.* FROM permissions p INNER JOIN role_permission rp ON p.id = rp.permission_id WHERE rp.role_id = #{roleId}")
    List<Permission> findPermissionsByRoleId(@Param("roleId") Long roleId);
}
