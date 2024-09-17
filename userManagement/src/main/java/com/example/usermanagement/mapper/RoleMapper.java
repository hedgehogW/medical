package com.example.usermanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.usermanagement.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT r.* FROM roles r INNER JOIN user_role ur ON r.id = ur.role_id WHERE ur.user_id = #{userId}")
    List<Role> findRolesByUserId(@Param("userId") Long userId);
}
