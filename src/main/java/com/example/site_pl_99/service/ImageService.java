package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImageService{
    InputStream getById(Long id);
    ImageEntity save(ImageEntity entity, MultipartFile file);
    String getContentType(Long id);
}
