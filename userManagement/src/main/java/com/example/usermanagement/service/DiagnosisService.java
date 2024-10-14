package com.example.usermanagement.service;

import com.example.usermanagement.vi.SubmitIn;
import com.example.usermanagement.vo.OcrOut;
import com.example.usermanagement.vo.SubmitOut;
import com.example.usermanagement.vo.UploadImageOut;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DiagnosisService {

    SubmitOut submitResponse(SubmitIn in);

    UploadImageOut uploadResponse(MultipartFile file);

    OcrOut OcrResponse(MultipartFile file) throws IOException, TesseractException;

}
