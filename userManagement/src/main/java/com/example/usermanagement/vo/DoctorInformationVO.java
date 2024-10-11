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
public class DoctorInformationVO implements Serializable {

    private String doctorName;

    private String hospitalName;

    private String departmentName;

    private Integer doctorAge;

    private String doctorSex;

    private String jobGrade;

    private Long serviceTimes;

    private String phoneNumber;

    private String telephoneNumber;

    private String specialty;

    private String workEmail;

    public static final Long serialVersionUID = 123L;
}
