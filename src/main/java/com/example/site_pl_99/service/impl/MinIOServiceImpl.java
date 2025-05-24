package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.service.MinIOService;
import io.minio.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class MinIOServiceImpl implements MinIOService {
    private final MinioClient minioClient;

    public MinIOServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void upload(MultipartFile file, String bucketName) throws Exception{
        ensureBucketExists(bucketName);
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(file.getOriginalFilename())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );
    }

    @Override
    public boolean fileExists(String bucketName, String fileName) throws Exception {
        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            return true; // Файл найден
        } catch (io.minio.errors.ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchKey")) {
                return false; // Файл не найден
            } else {
                throw e; // Другие ошибки — пробрасываем дальше
            }
        }
    }

    @Override
    public InputStream streamFile(String bucketName, String fileName) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }

    @Override
    public String getContentType(String bucketName, String fileName) throws Exception {
        StatObjectResponse stat = minioClient.statObject(
                StatObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
        return stat.contentType();
    }

    private void ensureBucketExists(String bucketName) throws Exception {
        boolean found = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucketName).build()
        );
        if (!found) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder().bucket(bucketName).build()
            );
        }
    }
}