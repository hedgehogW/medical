package com.example.usermanagement.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wyz
 * @date 2024/10/12
 * @version 1.0.0
 */

@Data
@TableName("admin")
@ApiModel("管理员实体类")
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {

    @TableId("admin_id")
    private Long adminId;

    private Long userId;

    private String adminName;

    private Integer adminAge;

    private String adminSex;

    private String workEmail;

    private String phoneNumber;

    private String telephoneNumber;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}