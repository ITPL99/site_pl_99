package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.VideoNewsEntity;
import com.example.site_pl_99.mapper.NewsMapper;
import com.example.site_pl_99.repository.VideoNewsRepository;
import com.example.site_pl_99.service.MinIoService;
import com.example.site_pl_99.service.NewsService;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.service.VideoNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class VideoNewsServiceImpl implements VideoNewsService {
    private final MinIoService minIoService;
    private final UserService userService;
    private final NewsService newsService;
    private final VideoNewsRepository videoNewsRepository;
    @Value("${minio.bucket.name.video}")
    private String bucketName;

    @Autowired
    public VideoNewsServiceImpl(MinIoService minIoService, UserService userService, NewsService newsService, VideoNewsRepository videoNewsRepository) {
        this.minIoService = minIoService;
        this.userService = userService;
        this.newsService = newsService;
        this.videoNewsRepository = videoNewsRepository;
    }

    @Override
    public VideoNewsEntity upload(MultipartFile file, Long newsId, Long userId) {
        if (minIoService.fileExists(bucketName, file.getOriginalFilename())) {
            throw new RuntimeException("Файл с таким именем уже существует. Переименуйте файл и попробуйте снова");
        }
        VideoNewsEntity videoNews = new VideoNewsEntity()
                .setFileName(file.getOriginalFilename())
                .setUserCreated(userService.getById(userId))
                .setNewsEntity(NewsMapper.toNewsEntity(newsService.getNewsId(newsId), userService.getById(userId)));
        minIoService.upload(file, bucketName);
        videoNewsRepository.save(videoNews);
        return videoNews;
    }

    @Override
    public InputStream stream(Long id) {
        try {
            VideoNewsEntity videoNews = videoNewsRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого видео нет"));
            return minIoService.streamFile(bucketName, videoNews.getFileName());
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getContentType(Long id){
        try {
            VideoNewsEntity videoNews = videoNewsRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого видео нет"));
            return minIoService.getContentType(bucketName, videoNews.getFileName());
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
