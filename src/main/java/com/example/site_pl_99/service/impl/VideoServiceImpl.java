package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.VideoEntity;
import com.example.site_pl_99.repository.VideoRepository;
import com.example.site_pl_99.service.MinIoService;
import com.example.site_pl_99.service.VideoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
@Service
public class VideoServiceImpl implements VideoService {
    private final MinIoService minIoService;
    private final VideoRepository videoRepository;
    @Value("${minio.bucket.name.video}")
    private String bucketName;

    public VideoServiceImpl(MinIoService minIoService, VideoRepository videoRepository) {
        this.minIoService = minIoService;
        this.videoRepository = videoRepository;
    }

    @Override
    public InputStream getById(Long id) {
        VideoEntity videoNews = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого видео нет"));
        return minIoService.streamFile(bucketName, videoNews.getFileName());

    }

    @Override
    public VideoEntity save(VideoEntity entity, MultipartFile file) {
        if (minIoService.fileExists(bucketName, file.getOriginalFilename())) {
            throw new RuntimeException("Файл с таким именем уже существует. Переименуйте файл и попробуйте снова");
        }
        VideoEntity videoNews = new VideoEntity()
                .setFileName(file.getOriginalFilename());
        minIoService.upload(file, bucketName);
        videoRepository.save(videoNews);
        return videoNews;
    }

    @Override
    public String getContentType(Long id) {
        VideoEntity videoNews = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого видео нет"));
        return minIoService.getContentType(bucketName, videoNews.getFileName());
    }
}
