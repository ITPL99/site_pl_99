package com.example.site_pl_99.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImageMinIoService {
    InputStream getByFileName(String fileName);
    void save(MultipartFile file);
    String getContentType(String fileName);
}
