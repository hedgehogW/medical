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
@AllArgsConstructor
@NoArgsConstructor
public class AdminInformationVO implements Serializable {

    private String adminName;

    private String superAdminString;

    private Integer adminAge;

    private String adminSex;

    private String workEmail;

    private String phoneNumber;

    private String telephoneNumber;

    public static final Long serialVersionUID = 34567L;
}
