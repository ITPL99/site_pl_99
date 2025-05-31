package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.VideoNewsEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface VideoNewsService {
    VideoNewsEntity upload(MultipartFile file, Long newsId, UserEntity user);
    InputStream stream(Long id);
    String getContentType(Long id);
}
