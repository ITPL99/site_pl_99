package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.mapper.NewsMapper;
import com.example.site_pl_99.service.NewsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@SecurityRequirement(name = "bearerAuth")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/get-by-title")
    public ResponseEntity<?> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.getTitle(title)));
    }

    @GetMapping("/get-all-by-content-title")
    public ResponseEntity<?> findByContentTitle(@RequestParam String contentTitle) {
        return ResponseEntity.ok(newsService.getAllContentTitle(contentTitle).stream().map(NewsMapper::toNewsDtoResponse).toList());
    }

    @GetMapping("/get-all-by-content-sub-title")
    public ResponseEntity<?> findByContentSubTitle(@RequestParam String contentSubTitle) {
        return ResponseEntity.ok(newsService.getAllContentSubTitle(contentSubTitle).stream().map(NewsMapper::toNewsDtoResponse).toList());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.getById(id)));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody NewsDtoRequest entity) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.save(NewsMapper.toNewsEntity(entity))));
    }

    @DeleteMapping("/delete-by-id")
    public void deleteById(@RequestParam Long id) {
        newsService.deleteById(id);
    }
}
