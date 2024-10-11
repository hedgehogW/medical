package com.example.usermanagement.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wyz
 * @date 2024/10/12
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResumeVO implements Serializable {

    private String doctorName;

    private Integer doctorAge;

    private String doctorSex;

    private String jobGrade;

    private String hospitalName;

    private String departmentName;

    private Long serviceTimes;

    private String specialty;

    public static final Long serialVersionUID = 123L;
}
