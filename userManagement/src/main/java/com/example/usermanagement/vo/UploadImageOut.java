package com.example.usermanagement.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("Upload Image Out")
public class UploadImageOut implements Serializable {

    public static final Long serialVerionUID = 1L;

    private Boolean result;

    private String imageUrl;

}
