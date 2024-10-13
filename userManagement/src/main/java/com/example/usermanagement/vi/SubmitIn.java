package com.example.usermanagement.vi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("SubmitIn")
public class SubmitIn implements Serializable {

    private static final Long serialVersionUID = 2345L;

    @NotBlank
    private Long hospitalId;

    @NotBlank
    private Long departmentId;

    @NotBlank
    private Long doctorId;

    @NotBlank
    private String description;

    private String imageUrl;

}
