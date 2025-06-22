package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.ImageDto;
import com.example.site_pl_99.mapper.ImageMapper;
import com.example.site_pl_99.service.ImageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@SecurityRequirement(name = "bearerAuth")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/save")
    public ResponseEntity<ImageDto> save(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.save(image)));
    }

    @GetMapping("/get-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(imageService.getContentType(id)))
                .body(new InputStreamResource(imageService.getById(id)));
    }

    @GetMapping("/get-file-by-name/{file_name}")
    public ResponseEntity<InputStreamResource> getByFileName(@PathVariable("file_name") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageService.getContentType(fileName)))
                .body(new InputStreamResource(imageService.getByFileName(fileName)));
    }

}
