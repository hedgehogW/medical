package com.example.usermanagement.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("permissions")
public class Permission {
    private Long id;
    private String permissionName;
}
