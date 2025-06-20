package com.example.site_pl_99.controller;

import com.example.site_pl_99.entity.VideoEntity;
import com.example.site_pl_99.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/video")
public class VideoController {
    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/save")
    public ResponseEntity<VideoEntity> save(@ModelAttribute VideoEntity videoEntity, @RequestParam("video") MultipartFile video) {
        return ResponseEntity.ok(videoService.save(videoEntity, video));
    }

    @GetMapping("/stream-file-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(videoService.getContentType(id)))
                .body(videoService.getById(id));
    }
}
