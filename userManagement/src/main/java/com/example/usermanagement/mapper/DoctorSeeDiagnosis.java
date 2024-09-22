package com.example.usermanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.usermanagement.model.PreDiagnosis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DoctorSeeDiagnosis extends BaseMapper<PreDiagnosis> {
    //根据病人ID查找病历
    @Select("SELECT * FROM  pre_diagnosis WHERE  patient_id = #{patientId}")
    List<PreDiagnosis> selectPreDiagnosis(@Param("patientId") Long patientId);
}