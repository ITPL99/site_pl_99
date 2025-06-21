package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.ImageEntity;
import com.example.site_pl_99.repository.ImageRepository;
import com.example.site_pl_99.service.ImageService;
import com.example.site_pl_99.service.MinIoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService {
    private final MinIoService minIoService;
    private final ImageRepository imageRepository;
    @Value("${minio.bucket.name.images}")
    private String bucketName;

    public ImageServiceImpl(MinIoService minIoService, ImageRepository imageRepository) {
        this.minIoService = minIoService;
        this.imageRepository = imageRepository;
    }

    @Override
    public InputStream uploadById(Long id) {
        ImageEntity imageNews = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого фото нет"));
        return minIoService.streamFile(bucketName, imageNews.getFileName());
    }
    @Override
    public InputStream uploadByFileName(String fileName) {
        return minIoService.streamFile(bucketName, fileName);
    }

    @Override
    public ImageEntity save( MultipartFile file) {
        if (minIoService.fileExists(bucketName, file.getOriginalFilename())) {
            throw new RuntimeException("Файл с таким именем уже существует. Переименуйте файл и попробуйте снова");
        }
        minIoService.upload(file, bucketName);
        ImageEntity imageNews = new ImageEntity()
                .setFileName(file.getOriginalFilename());
        imageRepository.save(imageNews);
        return imageNews;
    }

    @Override
    public String getContentType(Long id){
        ImageEntity image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого фото нет"));
        return minIoService.getContentType(bucketName, image.getFileName());
    }

    @Override
    public String getContentType(String fileName){
        return minIoService.getContentType(bucketName, fileName);
    }

}
