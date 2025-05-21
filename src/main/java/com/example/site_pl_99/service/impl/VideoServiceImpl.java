package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.VideoNewsDTORequestRegister;
import com.example.site_pl_99.entity.VideoNews;
import com.example.site_pl_99.repository.VideoRepository;
import com.example.site_pl_99.service.MinIOService;
import com.example.site_pl_99.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class VideoServiceImpl implements VideoService {

    private final MinIOService minIOService;
    private final VideoRepository repository;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Autowired
    public VideoServiceImpl(MinIOService minIOService, VideoRepository repository) {
        this.minIOService = minIOService;
        this.repository = repository;
    }

    @Override
    public void save(MultipartFile file, VideoNewsDTORequestRegister videoNewsDTORequestRegister) throws Exception{
        String name = file.getOriginalFilename();
        boolean fileExists = minIOService.fileExists(bucketName, name);
        if (fileExists) {
            throw new IllegalArgumentException("Файл с таким именем уже существует. Переименуйте файл и попробуйте снова");
        }
        minIOService.upload(file, bucketName);
        VideoNews videoNews = new VideoNews();
        videoNews.setId(videoNewsDTORequestRegister.getId())
                .setNews(videoNewsDTORequestRegister.getNews());
        repository.save(videoNews);
    }

    @Override
    public InputStream stream(Long id) throws Exception{
        VideoNews videoNews = repository.findById(id).orElseThrow(() -> new RuntimeException("Такого видео нет"));
        return minIOService.streamFile(bucketName, videoNews.getName());
    }

    @Override
    public String getContentType(Long id) throws Exception{
        VideoNews videoNews = repository.findById(id).orElseThrow(() -> new RuntimeException("Такого видео нет"));
        return minIOService.getContentType(bucketName, videoNews.getName());
    }
}
