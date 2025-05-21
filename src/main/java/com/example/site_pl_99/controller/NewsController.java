package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.VideoNewsDTORequestRegister;
import com.example.site_pl_99.entity.ImageNews;
import com.example.site_pl_99.entity.News;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.VideoNews;
import com.example.site_pl_99.service.ImageService;
import com.example.site_pl_99.service.NewsService;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;
    private final ImageService imageService;
    private final VideoService videoService;
    private final UserService userService;

    @Autowired
    public NewsController(UserService userService,NewsService newsService, ImageService imageService, VideoService videoService) {
        this.newsService = newsService;
        this.imageService = imageService;
        this.videoService = videoService;
        this.userService = userService;
    }

    @PostMapping("/create-news/{id}")
    public ResponseEntity<?> createNews(@RequestBody News news, @PathVariable Long id){
        UserEntity user = userService.getById(id);
        if (!user.getRoleEntityList().get(0).equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Только Админ может создовать");
        }
        newsService.save(news);
        return ResponseEntity.ok().build();
    }

    @PostMapping("upload_images/{id}/{newsId}")
    public ResponseEntity<String> saveImages(@RequestParam("file")MultipartFile file, @PathVariable Long id,@PathVariable Long newsId){
        try {
            ImageNews image = new ImageNews().setNews(newsService.findNewsById(id)).setName(file.getOriginalFilename());
            imageService.save(file,image);
            return ResponseEntity.ok("Файл успешно загружен: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка загрузки файла: " + e.getMessage());
        }
    }

    @PostMapping("/upload-video/{id}/{newsId}")
    public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file, @PathVariable Long id,@PathVariable Long newsId){
        try {
            VideoNewsDTORequestRegister videoNews = new VideoNewsDTORequestRegister().setNews(newsService.findNewsById(id)).setName(file.getName());
            videoService.save(file,videoNews);
            return ResponseEntity.ok("Файл успешно загружен: " + file.getOriginalFilename());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Загрузка файла: " + e.getMessage());
        }
    }

    @GetMapping("stream-images/{id}")
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
