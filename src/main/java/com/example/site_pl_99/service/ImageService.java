package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.ImageNews;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImageService {
    String save(MultipartFile file, ImageNews imageNews) throws Exception;
    InputStream getImagesById(Long id) throws Exception;
}
