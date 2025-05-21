package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.VideoNewsDTORequestRegister;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface VideoService {
    void save(MultipartFile file, VideoNewsDTORequestRegister videoNewsDTORequestRegister) throws Exception;
    InputStream stream(Long id) throws Exception;
    String getContentType(Long id) throws Exception;
}
