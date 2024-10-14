package com.example.usermanagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.usermanagement.adviser.BaseException;
import com.example.usermanagement.adviser.ErrorCode;
import com.example.usermanagement.mapper.DiagnosisMapper;
import com.example.usermanagement.model.Diagnosis;
import com.example.usermanagement.service.DiagnosisService;
import com.example.usermanagement.service.SessionService;
import com.example.usermanagement.utils.AliOSSUtils;
import com.example.usermanagement.vi.SubmitIn;
import com.example.usermanagement.vo.OcrOut;
import com.example.usermanagement.vo.SubmitOut;
import com.example.usermanagement.vo.UploadImageOut;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wyz
 * @date 2024/10/14
 * ocr集成、图片上传、病例上传
 */

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private DiagnosisMapper diagnosisMapper;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private Tesseract tesseract;



    @Override
    public SubmitOut submitResponse(SubmitIn in) {

        SubmitOut submitOut = new SubmitOut();
        String user = sessionService.getDataFromSession("userinfo");
        JSONObject userJson = JSON.parseObject(user);
        Long userId =  (Long) userJson.get("id");
        String imageUrl = (String) redisTemplate.opsForValue().get("imageUrl");
        String description1 = in.getDescription();
        String description2 = "";
        if (redisTemplate.hasKey("description")){
            description2 = (String) redisTemplate.opsForValue().get("description");
        }

        Diagnosis diagnosis = new Diagnosis();
        BeanUtils.copyProperties(in, diagnosis);

        diagnosis.setPatientId(userId);
        diagnosis.setCreateTime(new Date());
        diagnosis.setUpdateTime(new Date());
        diagnosis.setStatus(true);
        diagnosis.setImageUrl(imageUrl);
        diagnosis.setDescription(description1+'\n'+description2);

//        diagnosisMapper.insert(diagnosis);
        try {
            diagnosisMapper.insert(diagnosis);
            Long id = diagnosis.getDiagnosisId();
            if(ObjectUtils.isNull(id)) {
                throw new BaseException(ErrorCode.SERVER_ERROR, "insert error");
            }
        } catch (BaseException e){
            submitOut.setResult(false);
            return submitOut;
        }
        submitOut.setResult(true);
        return submitOut;
    }

    @Override
    public UploadImageOut uploadResponse(MultipartFile file) {

        UploadImageOut out = new UploadImageOut();
        try{
            //调用阿里云OSS工具类进行文件上传
            String url = aliOSSUtils.upload(file);
            out.setImageUrl(url);
            redisTemplate.opsForValue().set("imageUrl",url);
            if(ObjectUtils.isNull(url)) {
                throw new BaseException(ErrorCode.SERVER_ERROR, "upload error");
            }
        }catch (BaseException e) {
            out.setResult(false);
            return out;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.setResult(true);
        return out;
    }

    @Override
    public OcrOut OcrResponse(MultipartFile file) throws IOException, TesseractException {

        OcrOut ocrOut = new OcrOut();
        // 转换
        InputStream sbs = new ByteArrayInputStream(file.getBytes());
        BufferedImage bufferedImage = ImageIO.read(sbs);
        try{
            // 对图片进行文字识别
            String description = tesseract.doOCR(bufferedImage);
            ocrOut.setDescription(description);
            redisTemplate.opsForValue().set("description",description);
            if(ObjectUtils.isNull(description)) {
                throw new BaseException(ErrorCode.SERVER_ERROR, "ocr Error");
            }
        }catch (BaseException e) {
            ocrOut.setResult(false);
            return ocrOut;
        }

        ocrOut.setResult(true);
        return ocrOut;
    }
}
