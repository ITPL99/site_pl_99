package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.VideoDto;
import com.example.site_pl_99.entity.VideoEntity;
import com.example.site_pl_99.mapper.VideoMapper;
import com.example.site_pl_99.service.VideoMinIoService;
import com.example.site_pl_99.service.VideoService;
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
@RequestMapping("/api/video")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Видео", description = "Управление видеофайлами и их метаданными")
public class VideoController {
    private final VideoService videoService;
    private final VideoMinIoService minIoService;

    @Autowired
    public VideoController(VideoService videoService, VideoMinIoService minIoService) {
        this.videoService = videoService;
        this.minIoService = minIoService;
    }

    @Operation(
            summary = "Сохранить видео в MinIO и базу данных",
            description = "Загружает видеофайл в хранилище и сохраняет информацию о нем в базе данных",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "object", format = "binary")
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Видео успешно загружено", content = @Content(schema = @Schema(implementation = VideoDto.class)))
            }
    )
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VideoDto> save(@RequestParam("video") MultipartFile video) {
        minIoService.save(video);
        VideoEntity videoEntity = new VideoEntity().setFileName(video.getOriginalFilename());
        return ResponseEntity.ok(VideoMapper.mapEntityToDto(videoService.save(videoEntity)));
    }

    @Operation(
            summary = "Получить видеопоток по ID",
            description = "Возвращает потоковое воспроизведение видео по ID из MinIO"
    )
    @GetMapping("/stream-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getById(
            @PathVariable("id") @Parameter(description = "ID видеофайла") Long id) {
        String fileName = videoService.getById(id).getFileName();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @Operation(
            summary = "Получить файл по имени",
            description = "Возвращает видеофайл из MinIO по имени"
    )
    @GetMapping("/get-file-by-name/{file_name}")
    public ResponseEntity<InputStreamResource> getByFileName(
            @PathVariable("file_name") @Parameter(description = "Имя видеофайла") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @Operation(
            summary = "Получить DTO видео по ID",
            description = "Возвращает метаданные видео по ID"
    )
    @GetMapping("/get-video-by-id/{id}")
    public ResponseEntity<VideoDto> getImagesById(
            @PathVariable("id") @Parameter(description = "ID видео") Long id) {
        return ResponseEntity.ok(VideoMapper.mapEntityToDto(videoService.getById(id)));
    }

    @Operation(
            summary = "Получить DTO видео по имени файла",
            description = "Возвращает метаданные видео по имени файла"
    )
    @GetMapping("/get-video-by-fileName/{file_name}")
    public ResponseEntity<VideoDto> getImagesByFileName(
            @PathVariable("file_name") @Parameter(description = "Имя файла видео") String fileName) {
        return ResponseEntity.ok(VideoMapper.mapEntityToDto(videoService.getByFileName(fileName)));
    }
}
