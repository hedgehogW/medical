package com.example.usermanagement.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("roles")
public class Role {
    private Long id;
    private String roleName;

}