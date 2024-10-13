package com.example.usermanagement.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("SubmitOut")
public class SubmitOut implements Serializable {

    private static final Long serialVersionUID = 4234L;

    private Boolean result;

}
