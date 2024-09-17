package com.example.usermanagement.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("user")
public class UserInfo {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    @TableField("is_enable")
    private Boolean isEnable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}