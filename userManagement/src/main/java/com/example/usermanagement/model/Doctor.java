package com.example.usermanagement.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("doctor")
@ApiModel("医生实体类")
@NoArgsConstructor
@AllArgsConstructor
public class Doctor implements Serializable {

    @TableId("doctor_id")
    private Long doctorId;

    private Long userId;

    private String doctorName;

    private Long hospitalId;

    private Long departmentId;

    private Integer doctorAge;

    private String doctorSex;

    private String jobGrade;

    private Long serviceTimes;

    private String phoneNumber;

    private String telephoneNumber;

    private String specialty;

    private String workEmail;

    private Boolean status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}