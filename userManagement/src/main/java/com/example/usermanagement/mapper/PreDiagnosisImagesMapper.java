package com.example.usermanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.usermanagement.model.PreDiagnosisImages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PreDiagnosisImagesMapper extends BaseMapper<PreDiagnosisImages> {
    @Select("SELECT * FROM pre_diagnosis_images WHERE pre_diagnosis_id = #{preDiagnosisId}")
    public List<PreDiagnosisImages> findAllByPreDiagnosisId(@Param("preDiagnosisId")Long preDiagnosisId);
}