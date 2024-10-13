package com.example.usermanagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.usermanagement.adviser.BaseException;
import com.example.usermanagement.adviser.ErrorCode;
import com.example.usermanagement.mapper.DiagnosisMapper;
import com.example.usermanagement.model.Diagnosis;
import com.example.usermanagement.service.DiagnosisService;
import com.example.usermanagement.service.SessionService;
import com.example.usermanagement.vi.SubmitIn;
import com.example.usermanagement.vo.SubmitOut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.Date;


@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private DiagnosisMapper diagnosisMapper;

    @Autowired
    private SessionService sessionService;

    @Override
    public SubmitOut submitResponse(SubmitIn in) {

        SubmitOut submitOut = new SubmitOut();
        String user = sessionService.getDataFromSession("userinfo");
        JSONObject userJson = JSON.parseObject(user);
        Long userId =  (Long) userJson.get("id");

        Diagnosis diagnosis = new Diagnosis();
        BeanUtils.copyProperties(in, diagnosis);

        diagnosis.setPatientId(userId);
        diagnosis.setCreateTime(new Date());
        diagnosis.setUpdateTime(new Date());
        diagnosis.setStatus(true);

        diagnosisMapper.insert(diagnosis);
//        try {
//            diagnosisMapper.insert(diagnosis);
//            Long id = diagnosis.getDiagnosisId();
//            if(ObjectUtils.isNull(id)) {
//                throw new BaseException(ErrorCode.SERVER_ERROR, "insert error");
//            }
//        } catch (BaseException e){
//            submitOut.setResult(false);
//            return submitOut;
//        }
        submitOut.setResult(true);
        return submitOut;
    }
}
