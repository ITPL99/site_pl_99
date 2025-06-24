package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.NewsDtoPreviewResponse;
import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.mapper.NewsMapper;
import com.example.site_pl_99.service.NewsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<NewsDtoResponse> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.getByTitle(title)));
    }

    @GetMapping("/get-all-by-content-title")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findByContentTitle(@RequestParam String contentTitle) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllByContentTitle(contentTitle)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findAllNews(){
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAll()));
    }

    @GetMapping("/get-all-full")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findAllFullNews(){
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllFull()));
    }

    @GetMapping("/get-all-by-content-sub-title")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findByContentSubTitle(@RequestParam String contentSubTitle) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllByContentSubTitle(contentSubTitle)));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<NewsDtoResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.getById(id)));
    }

    @PostMapping("/save")
    public ResponseEntity<NewsDtoResponse> save(@RequestBody NewsDtoRequest entity) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.addNews(NewsMapper.toNewsEntity(entity))));
    }

    @DeleteMapping("/delete-by-id")
    public void deleteById(@RequestParam Long id) {
        newsService.deleteById(id);
    }

    @GetMapping("/get-by-status")
    public ResponseEntity<List<NewsDtoPreviewResponse>> getByStatus(@RequestParam String status) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllByActiveStatus(status)));
    }
}
