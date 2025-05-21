package com.example.site_pl_99.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface MinIOService {
    InputStream streamFile(String bucketName, String fileName) throws Exception;
    String getContentType(String bucketName, String fileName) throws Exception;
    void upload(MultipartFile file, String bucketName) throws Exception;
    boolean fileExists(String bucketName, String fileName) throws Exception;
}
