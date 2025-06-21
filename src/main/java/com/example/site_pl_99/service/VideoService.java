package com.example.site_pl_99.service;



import com.example.site_pl_99.entity.VideoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface VideoService {
    InputStream getById(Long id);
    VideoEntity save(VideoEntity entity, MultipartFile file);
    String getContentType(Long id);
}
