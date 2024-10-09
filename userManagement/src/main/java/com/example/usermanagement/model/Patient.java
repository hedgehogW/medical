package com.example.usermanagement.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wyz
 * @date 2024/10/9
 * @version 1.0.0
 * 患者实体类创建
 */
@Data
@ApiModel("patient")
@NoArgsConstructor
@AllArgsConstructor
@TableName("patient")
public class Patient implements Serializable {

    private Long patientId;

    private Long userId;

    private String patientName;

    private Integer patientAge;

    private String phoneNumber;

    private String telephoneNumber;

    private String email;

    private String homeAddress;

    private Date createTime;

    private Date updateTime;

    private Boolean state;

    private static final long serialVersionUID = 1L;
}