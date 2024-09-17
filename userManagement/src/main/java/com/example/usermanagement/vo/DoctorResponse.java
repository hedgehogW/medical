package com.example.usermanagement.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {
    private Long doctorId;
    private String doctorName;
    private String doctorSpecialty;
}
