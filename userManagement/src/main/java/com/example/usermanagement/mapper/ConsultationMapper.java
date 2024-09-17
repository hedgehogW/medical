package com.example.usermanagement.mapper;

import com.example.usermanagement.model.Consultation;

public interface ConsultationMapper {
    int deleteByPrimaryKey(Long consultationId);

    int insert(Consultation record);

    int insertSelective(Consultation record);

    Consultation selectByPrimaryKey(Long consultationId);

    int updateByPrimaryKeySelective(Consultation record);

    int updateByPrimaryKey(Consultation record);
}