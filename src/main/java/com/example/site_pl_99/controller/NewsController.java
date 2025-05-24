package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDTO;
import com.example.site_pl_99.dto.NewsDTO;
import com.example.site_pl_99.entity.Course;
import com.example.site_pl_99.entity.ImageNews;
import com.example.site_pl_99.entity.News;
import com.example.site_pl_99.entity.VideoNews;
import com.example.site_pl_99.service.ImageService;
import com.example.site_pl_99.service.NewsService;
import com.example.site_pl_99.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;
    private final ImageService imageService;
    private final VideoService videoService;

    @Autowired
    public NewsController(NewsService newsService, ImageService imageService, VideoService videoService) {
        this.newsService = newsService;
        this.imageService = imageService;
        this.videoService = videoService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/create-news")
    public ResponseEntity<?> createNews(@RequestBody News news){
        newsService.save(news);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-news-id/{id}")
    public NewsDTO getCourseById(@PathVariable Long id){
        News news = newsService.findNewsById(id);
        return new NewsDTO(news);
    }

    @GetMapping("/get-all-news")
    public List<NewsDTO> getAll(){
        List<NewsDTO> newsDTOS = new ArrayList<>();
        for(News news : newsService.getAll()){
            NewsDTO newsDTO = new NewsDTO(news);
            newsDTOS.add(newsDTO);
        }
        return newsDTOS;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/upload_images/{newsId}")
    public ResponseEntity<String> saveImages(@RequestParam("file")MultipartFile file,@PathVariable Long newsId){
        try {
            ImageNews image = new ImageNews().setNews(newsService.findNewsById(newsId)).setName(file.getOriginalFilename());
            imageService.save(file,image);
            return ResponseEntity.ok("Файл успешно загружен: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка загрузки файла: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/upload-video/{newsId}")
    public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file,@PathVariable Long newsId){
        try {
            VideoNews videoNews = new VideoNews().setNews(newsService.findNewsById(newsId)).setName(file.getName());
            videoService.save(file,videoNews);
            return ResponseEntity.ok("Файл успешно загружен: " + file.getOriginalFilename());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Загрузка файла: " + e.getMessage());
        }
    }

    @GetMapping("/stream-images/{id}")
    private ResponseEntity<InputStream> getImagesId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(imageService.getImagesById(id));
    }

    @GetMapping("/stream-video/{id}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable Long id) throws Exception {
        try {
            InputStreamResource inputStreamResource =new InputStreamResource(videoService.stream(id));
            String contentType = videoService.getContentType(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(inputStreamResource);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
