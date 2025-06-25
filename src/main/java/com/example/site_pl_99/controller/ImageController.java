package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.ImageDto;
import com.example.site_pl_99.entity.ImageEntity;
import com.example.site_pl_99.mapper.ImageMapper;
import com.example.site_pl_99.service.ImageMinIoService;
import com.example.site_pl_99.service.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Image Controller", description = "Загрузка, получение и управление изображениями")
public class ImageController {

    private final ImageService imageService;
    private final ImageMinIoService minIoService;

    @Autowired
    public ImageController(ImageService imageService, ImageMinIoService minIoService) {
        this.imageService = imageService;
        this.minIoService = minIoService;
    }

    @Operation(
            summary = "Загрузить изображение",
            description = "Сохраняет изображение в MinIO и сохраняет метаданные в базе данных",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Файл успешно загружен")
            }
    )
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageDto> save(
            @Parameter(description = "Файл изображения", required = true)
            @RequestParam("image") MultipartFile image) {
        minIoService.save(image);
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(image.getOriginalFilename());
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.save(imageEntity)));
    }

    @Operation(
            summary = "Получить файл изображения по ID",
            description = "Возвращает файл изображения из MinIO по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Файл найден")
            }
    )
    @GetMapping("/get-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getById(
            @Parameter(description = "ID изображения", required = true)
            @PathVariable("id") Long id) {
        String fileName = imageService.getById(id).getFileName();
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @Operation(
            summary = "Получить файл изображения по имени файла",
            description = "Возвращает файл изображения по имени файла из MinIO",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Файл получен")
            }
    )
    @GetMapping("/get-file-by-name/{file_name}")
    public ResponseEntity<InputStreamResource> getByFileName(
            @Parameter(description = "Имя файла изображения", required = true)
            @PathVariable("file_name") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @Operation(
            summary = "Получить метаданные изображения по ID",
            description = "Возвращает DTO изображения по его ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены метаданные изображения по ID")
            }
    )
    @GetMapping("/get-images-by-id/{id}")
    public ResponseEntity<ImageDto> getImagesById(
            @Parameter(description = "ID изображения", required = true)
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.getById(id)));
    }

    @Operation(
            summary = "Получить метаданные изображения по имени файла",
            description = "Возвращает DTO изображения по имени файла",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены метаданные изображения по ID")
            }
    )
    @GetMapping("/get-images-by-fileName/{file_name}")
    public ResponseEntity<ImageDto> getImagesByFileName(
            @Parameter(description = "Имя файла изображения", required = true)
            @PathVariable("file_name") String fileName) {
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.getByFileName(fileName)));
    }
}
