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
@AllArgsConstructor
@NoArgsConstructor
@TableName("pre_diagnosis")
public class PreDiagnosis implements Serializable {
    @TableId("id")
    private Long id;

    private Long patientId;

    private Long doctorId;

    private LocalDateTime submitTime;

    private String richText;

    private static final long serialVersionUID = 1L;
}