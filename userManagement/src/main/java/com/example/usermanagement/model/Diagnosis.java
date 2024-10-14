package com.example.usermanagement.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("diagnosis")
public class Diagnosis implements Serializable {

    @TableId("diagnosis_id")
    private Long diagnosisId;

    private Long hospitalId;

    private Long departmentId;

    private Long doctorId;

    private Long patientId;

    private String description;

    private String imageUrl;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}