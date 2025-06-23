package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.service.MinIoService;
import com.example.site_pl_99.service.VideoMinIoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
public class VideoMinIoServiceImpl implements VideoMinIoService {
    private final MinIoService minIoService;
    @Value("${minio.bucket.name.video}")
    private String bucketName;

    @Autowired
    public VideoMinIoServiceImpl(MinIoService minIoService) {
        this.minIoService = minIoService;
    }

    @Override
    public InputStream getByFileName(String fileName) {
        return minIoService.streamFile(bucketName, fileName);
    }

    @Override
    public void save(MultipartFile file) {
        if (minIoService.fileExists(bucketName, file.getOriginalFilename())) {
            throw new RuntimeException("Файл с таким именем уже существует. Переименуйте файл и попробуйте снова");
        }
        minIoService.upload(file, bucketName);
    }

    @Override
    public String getContentType(String fileName) {
        return minIoService.getContentType(bucketName, fileName);
    }
}
