package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImageService{
    InputStream uploadById(Long id);

    InputStream uploadByFileName(String fileName);

    ImageEntity save(MultipartFile file);
    String getContentType(Long id);

    String getContentType(String fileName);
}
