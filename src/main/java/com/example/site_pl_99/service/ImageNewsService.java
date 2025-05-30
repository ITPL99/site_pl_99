package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.ImageNewsEntity;
import com.example.site_pl_99.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImageNewsService {
    ImageNewsEntity upload(MultipartFile file, Long newsId, UserEntity user);
    String getContentType(Long id);
    InputStream streamFile(Long id);
}
