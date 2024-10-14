package com.example.usermanagement.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("OCR Out")
public class OcrOut implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Boolean result;

    private String description;

}
