package com.example.usermanagement.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pre_diagnosis_images")
public class PreDiagnosisImages implements Serializable {
    @TableId("id")
    private Long id;

    private Long preDiagnosisId;

    private String imageUrl;

    private LocalDateTime uploadTime;

    private static final long serialVersionUID = 1L;
}