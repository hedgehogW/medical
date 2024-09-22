package com.example.usermanagement.vi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreDiagnosisRequest {
    private Long patientId; // 患者ID
    private Long doctorId;  // 医生ID
    private String richText;  // 病情描述（富文本）
    private List<String> imageUrls; // 图片URL列表
}
