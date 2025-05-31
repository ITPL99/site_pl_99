package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.ImageNewsEntity;
import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.repository.ImageNewsRepository;
import com.example.site_pl_99.service.ImageNewsService;
import com.example.site_pl_99.service.MinIoService;
import com.example.site_pl_99.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class ImageNewsServiceImpl implements ImageNewsService {
    private final ImageNewsRepository imageNewsRepository;
    private final NewsService newsService;
    private final MinIoService minIoService;
    @Value("${minio.bucket.name.imagesNews}")
    private String bucketName;

    @Autowired
    public ImageNewsServiceImpl(MinIoService minIoService, ImageNewsRepository imageNewsRepository, NewsService newsService) {
        this.imageNewsRepository= imageNewsRepository;
        this.minIoService= minIoService;
        this.newsService = newsService;
    }

    @Override
    public ImageNewsEntity upload(MultipartFile file, Long newsId, UserEntity user) {
        try {
            if (minIoService.fileExists(bucketName, file.getOriginalFilename())) {
                throw new RuntimeException("Файл с таким именем уже существует. Переименуйте файл и попробуйте снова");
            }
            NewsEntity newsEntity = newsService.getNewsId(newsId);
            List<ImageNewsEntity> imageNewsEntityList = newsEntity.getImages();
            ImageNewsEntity imageNews = new ImageNewsEntity()
                    .setFileName(file.getOriginalFilename())
                    .setNewsEntity(newsEntity)
                    .setUserCreated(user);
            imageNewsEntityList.add(imageNews);
            newsService.saveNews(newsEntity);
            minIoService.upload(file, bucketName);
            return imageNewsRepository.save(imageNews);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String getContentType(Long id){
        ImageNewsEntity imageNews = imageNewsRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого фото нет"));
        return minIoService.getContentType(bucketName, imageNews.getFileName());
    }

    @Override
    public InputStream streamFile(Long id){
        ImageNewsEntity imageNews = imageNewsRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого фото нет"));
        return minIoService.streamFile(bucketName, imageNews.getFileName());
    }

}
