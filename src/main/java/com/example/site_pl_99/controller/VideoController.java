package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.VideoDto;
import com.example.site_pl_99.entity.VideoEntity;
import com.example.site_pl_99.mapper.VideoMapper;
import com.example.site_pl_99.service.VideoMinIoService;
import com.example.site_pl_99.service.VideoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/video")
@SecurityRequirement(name = "bearerAuth")
public class VideoController {
    private final VideoService videoService;
    private final VideoMinIoService minIoService;

    @Autowired
    public VideoController(VideoService videoService, VideoMinIoService minIoService) {
        this.videoService = videoService;
        this.minIoService = minIoService;
    }

    @PostMapping("/save")
    public ResponseEntity<VideoDto> save(@RequestParam("video") MultipartFile video) {
        minIoService.save(video);
        VideoEntity videoEntity = new VideoEntity().setFileName(video.getOriginalFilename());
        return ResponseEntity.ok(VideoMapper.mapEntityToDto(videoService.save(videoEntity)));
    }

    @GetMapping("/stream-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(videoService.getById(id).getFileName())))
                .body(new InputStreamResource(minIoService.getByFileName(videoService.getById(id).getFileName())));
    }

    @GetMapping("/get-file-by-name/{file_name}")
    public ResponseEntity<InputStreamResource> getByFileName(@PathVariable("file_name") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @GetMapping("/get-video-by-id/{id}")
    public ResponseEntity<VideoDto> getImagesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(VideoMapper.mapEntityToDto(videoService.getById(id)));
    }

    @GetMapping("/get-video-by-fileName/{file_name}")
    public ResponseEntity<VideoDto> getImagesByFileName(@PathVariable("file_name") String fileName) {
        return ResponseEntity.ok(VideoMapper.mapEntityToDto(videoService.getByFileName(fileName)));
    }
}
