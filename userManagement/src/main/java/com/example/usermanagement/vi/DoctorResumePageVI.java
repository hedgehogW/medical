package com.example.usermanagement.vi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wyz
 * @date 2024/10/12
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResumePageVI {

    private Long hospitalId;

    private Long departmentId;

    private Integer pageNum;

    private Integer pageSize;
}
