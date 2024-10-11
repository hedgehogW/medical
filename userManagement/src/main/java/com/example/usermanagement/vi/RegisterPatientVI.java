package com.example.usermanagement.vi;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @date 2024/10/11
 * @author wyz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPatientVI implements Serializable {

    private String username;

    private String password;

    private String patientName;

    private Integer patientAge;

    private String phoneNumber;

    private String telephoneNumber;

    private String email;

    private String homeAddress;

    public static final Long serialVersionUID = -123456L;
}
