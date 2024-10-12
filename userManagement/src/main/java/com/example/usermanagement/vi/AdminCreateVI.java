package com.example.usermanagement.vi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateVI {

    private String username;

    private String password;

    private String superAdmin;

    private String adminName;

    private Integer adminAge;

    private String adminSex;

    private String workEmail;

    private String phoneNumber;

    private String telephoneNumber;

}
