package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.ImageDto;
import com.example.site_pl_99.entity.ImageEntity;
import com.example.site_pl_99.mapper.ImageMapper;
import com.example.site_pl_99.service.ImageMinIoService;
import com.example.site_pl_99.service.ImageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@SecurityRequirement(name = "bearerAuth")
public class ImageController {
    private final ImageService imageService;
    private final ImageMinIoService minIoService;

    @Autowired
    public ImageController(ImageService imageService, ImageMinIoService minIoService) {
        this.imageService = imageService;
        this.minIoService = minIoService;
    }

    @PostMapping("/save")
    public ResponseEntity<ImageDto> save(@RequestParam("image") MultipartFile image) {
        minIoService.save(image);
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(image.getOriginalFilename());
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.save(imageEntity)));
    }

    @GetMapping("/get-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(imageService.getById(id).getFileName())))
                .body(new InputStreamResource(minIoService.getByFileName(imageService.getById(id).getFileName())));
    }

    @GetMapping("/get-file-by-name/{file_name}")
    public ResponseEntity<InputStreamResource> getByFileName(@PathVariable("file_name") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @GetMapping("/get-images-by-id/{id}")
    public ResponseEntity<ImageDto> getImagesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.getById(id)));
    }

    @GetMapping("/get-images-by-fileName/{file_name}")
    public ResponseEntity<ImageDto> getImagesByFileName(@PathVariable("file_name") String fileName) {
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.getByFileName(fileName)));
    }

}
