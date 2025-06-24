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
@Tag(name = "Изображения", description = "Управление загрузкой и получением изображений")
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
            description = "Сохраняет изображение в MinIO и в базу данных",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Изображение успешно сохранено",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageDto.class))),
                    @ApiResponse(responseCode = "400", description = "Ошибка загрузки файла", content = @Content)
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
            description = "Возвращает файл изображения в формате stream",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Файл найден и возвращён"),
                    @ApiResponse(responseCode = "404", description = "Файл не найден")
            }
    )
    @GetMapping("/get-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getById(
            @PathVariable("id") @Parameter(description = "ID изображения") Long id) {
        String fileName = imageService.getById(id).getFileName();
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @Operation(
            summary = "Получить файл изображения по имени файла",
            description = "Возвращает файл изображения по имени",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Файл найден"),
                    @ApiResponse(responseCode = "404", description = "Файл не найден")
            }
    )
    @GetMapping("/get-file-by-name/{file_name}")
    public ResponseEntity<InputStreamResource> getByFileName(
            @PathVariable("file_name") @Parameter(description = "Имя файла изображения") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @Operation(
            summary = "Получить DTO изображения по ID",
            description = "Возвращает метаинформацию изображения (DTO)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "DTO успешно возвращено"),
                    @ApiResponse(responseCode = "404", description = "Изображение не найдено")
            }
    )
    @GetMapping("/get-images-by-id/{id}")
    public ResponseEntity<ImageDto> getImagesById(
            @PathVariable("id") @Parameter(description = "ID изображения") Long id) {
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.getById(id)));
    }

    @Operation(
            summary = "Получить DTO изображения по имени файла",
            description = "Возвращает DTO объекта изображения по имени файла",
            responses = {
                    @ApiResponse(responseCode = "200", description = "DTO найдено"),
                    @ApiResponse(responseCode = "404", description = "Изображение не найдено")
            }
    )
    @GetMapping("/get-images-by-fileName/{file_name}")
    public ResponseEntity<ImageDto> getImagesByFileName(
            @PathVariable("file_name") @Parameter(description = "Имя файла изображения") String fileName) {
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.getByFileName(fileName)));
    }
}
