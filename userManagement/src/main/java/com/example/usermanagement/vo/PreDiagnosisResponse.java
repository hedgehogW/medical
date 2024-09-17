package com.example.usermanagement.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreDiagnosisResponse {
    private Long preDiagnosisId; // 预诊记录ID
    private Long patientId; // 患者ID
    private Long doctorId;  // 医生ID
    private String richText;  // 病情描述（富文本）
    private List<String> imageUrls; // 图片URL列表
    private LocalDateTime submitTime; // 提交时间

}
