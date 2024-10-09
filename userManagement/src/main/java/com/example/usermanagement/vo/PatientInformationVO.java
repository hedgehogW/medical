package com.example.usermanagement.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PatientInformationVO implements Serializable {

    private Long patientId;

    private String patientName;

    private Integer patientAge;

    private String phoneNumber;

    private String telephoneNumber;

    private String email;

    private String homeAddress;

    private static final long serialVersionUID = 1L;
}
