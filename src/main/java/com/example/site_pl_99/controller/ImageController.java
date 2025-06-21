package com.example.site_pl_99.controller;

import com.example.site_pl_99.entity.ImageEntity;
import com.example.site_pl_99.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/save")
    public ResponseEntity<ImageEntity> save(@ModelAttribute ImageEntity imageEntity,@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(imageService.save(imageEntity, image));
    }

    @GetMapping("/stream-file-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageService.getContentType(id)))
                .body(new InputStreamResource(imageService.getById(id)));
    }

}
