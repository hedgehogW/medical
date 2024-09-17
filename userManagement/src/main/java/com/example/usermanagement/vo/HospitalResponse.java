package com.example.usermanagement.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalResponse {
    private Long hospitalId;
    private String hospitalName;
}
