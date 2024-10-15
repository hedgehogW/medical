package com.example.usermanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.usermanagement.model.PreDiagnosis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PreDiagnosisMapper extends BaseMapper<PreDiagnosis> {
    @Select("SELECT * FROM pre_diagnosis WHERE doctor_id = #{doctorId} AND patient_id = #{patientId}")
    public List<PreDiagnosis> findAllByDoctorIdAndPatientId(@Param("doctorId") Long doctorId, @Param("patientId") Long patientId);

    @Select("SELECT patient_id FROM pre_diagnosis WHERE doctor_id = #{doctorId}")
    public List<Long> findPatientIdByDoctorId(@Param("doctorId")Long doctorId);

}