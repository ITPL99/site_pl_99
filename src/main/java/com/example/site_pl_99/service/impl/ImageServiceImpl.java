package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.ImageNews;
import com.example.site_pl_99.repository.ImageRepository;
import com.example.site_pl_99.service.ImageService;
import com.example.site_pl_99.service.MinIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService {
    private final MinIOService minIOService;
    private final ImageRepository imageRepository;

    @Value("${minio.busket-name}")
    private String bucketName;

    @Autowired
    public ImageServiceImpl(MinIOService minIOService, ImageRepository imageRepository) {
        this.minIOService = minIOService;
        this.imageRepository = imageRepository;
    }

    @Override
    public String save(MultipartFile file, ImageNews imageNews) throws Exception {
        String name = file.getOriginalFilename();
        boolean fileExists = minIOService.fileExists(bucketName, name);
        if(fileExists){
            throw new IllegalArgumentException("Файл с таким именем уже существует. Переименуйте файл и попробуйте снова");
        }
        minIOService.upload(file,bucketName);
        imageRepository.save(imageNews);
        return file.getOriginalFilename();
    }

    @Override
    public InputStream getImagesById(Long id) throws Exception{
        ImageNews imageNews = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Такой фотографии нет"));
        return minIOService.streamFile(bucketName, imageNews.getName());
    }
}
