package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.entity.ImageNewsEntity;
import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.mapper.NewsMapper;
import com.example.site_pl_99.service.ImageNewsService;
import com.example.site_pl_99.service.NewsService;
import com.example.site_pl_99.service.VideoNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;
    private final ImageNewsService imageNewsService;
    private final VideoNewsService videoNewsService;

    @Autowired
    public NewsController(NewsService newsService, ImageNewsService imageNewsService, VideoNewsService videoNewsService) {
        this.newsService = newsService;
        this.imageNewsService = imageNewsService;
        this.videoNewsService = videoNewsService;
    }

    @PostMapping("/add-news")
    public ResponseEntity<String> saveNews(@ModelAttribute NewsDtoRequest newsDto, @RequestParam(required = false) List<MultipartFile> file) {
        try {
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            NewsEntity newsEntity = newsService.saveNews(newsDto, user);
            List<ImageNewsEntity> imageNewsEntityList = new ArrayList<>();
            if(file != null && !file.isEmpty()) {
                for (MultipartFile file1 : file) {
                    imageNewsEntityList.add(imageNewsService.upload(
                            file1,
                            newsEntity.getId(),
                            user
                    ));
                }
            }
            newsEntity.setImages(imageNewsEntityList);
            newsService.saveNews(newsEntity);
            return ResponseEntity.ok().body("новость сохранена");
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка сохранении новости: " + e.getMessage());
        }
    }

    @GetMapping("/get-news-id/{id}")
    public ResponseEntity<?> getNewsId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(NewsMapper.toNewsDtoResponse(newsService.getNewsId(id)));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при получении новости: " + e.getMessage());
        }
    }

    @GetMapping("/get-all-news")
    public ResponseEntity<?> getAllNews() {
        try {
            return ResponseEntity.ok().body(NewsMapper.toNewsDtoResponseList(newsService.getAllNews()));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при получении новости: " +e.getMessage());
        }
    }

    @PostMapping("/upload-image-news/{newsId}")
    public ResponseEntity<?> upload(@RequestParam("file") List<MultipartFile> file, @PathVariable Long newsId) {
        try {
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            for (MultipartFile file1 : file) {
                imageNewsService.upload(
                        file1,
                        newsId,
                        user
                );
            }
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(500).body("Ошибка загрузки файла: " + e.getMessage());
        }
    }

    @GetMapping("/get-images-news/{id}")
    public ResponseEntity<?> streamImages(@PathVariable Long id){
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageNewsService.getContentType(id)))
                .body(new InputStreamResource(imageNewsService.streamFile(id)));
    }

    @PostMapping("/upload-video-news/{newsId}")
    public ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile file, @PathVariable Long newsId) {
        try {
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            videoNewsService.upload(
                    file,
                    newsId,
                    user
            );
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(500).body("Ошибка загрузки файла: " + e.getMessage());
        }
    }

    @GetMapping("/get-video-news/{id}")
    public ResponseEntity<?> streamVideo(@PathVariable Long id){
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(videoNewsService.getContentType(id)))
                .body(new InputStreamResource(videoNewsService.stream(id)));
    }
}
