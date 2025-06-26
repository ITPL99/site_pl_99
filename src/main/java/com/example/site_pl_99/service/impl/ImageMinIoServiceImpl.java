package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.service.ImageMinIoService;
import com.example.site_pl_99.service.MinIoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
public class ImageMinIoServiceImpl implements ImageMinIoService {
    private final MinIoService minIoService;
    @Value("${minio.bucket.name.images}")
    private String bucketName;

    @Autowired
    public ImageMinIoServiceImpl(MinIoService minIoService) {
        this.minIoService = minIoService;
    }

    @Override
    public InputStream getByFileName(String fileName) {
        return minIoService.streamFile(bucketName, fileName);
    }

    @Override
    public void save(MultipartFile file) {
        if (minIoService.fileExists(bucketName, file.getOriginalFilename())) {
            throw new RuntimeException("error.doubleName");
        }
        minIoService.upload(file, bucketName);
    }

    @Override
    public String getContentType(String fileName) {
        return minIoService.getContentType(bucketName, fileName);
    }

}
