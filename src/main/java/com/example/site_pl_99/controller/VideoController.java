package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.VideoDto;
import com.example.site_pl_99.entity.VideoEntity;
import com.example.site_pl_99.mapper.VideoMapper;
import com.example.site_pl_99.service.VideoMinIoService;
import com.example.site_pl_99.service.VideoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Загрузить видеофайл",
            description = "Сохраняет видеофайл в хранилище MinIO и метаинформацию в базу данных"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Видео успешно сохранено",
                    content = @Content(schema = @Schema(implementation = VideoDto.class))),
            @ApiResponse(responseCode = "400", description = "Некорректный файл", content = @Content)
    })
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VideoDto> save(
            @Parameter(description = "Видео файл", required = true)
            @RequestParam("video") MultipartFile video) {

        minIoService.save(video);
        VideoEntity videoEntity = new VideoEntity().setFileName(video.getOriginalFilename());
        return ResponseEntity.ok(VideoMapper.mapEntityToDto(videoService.save(videoEntity)));
    }

    @Operation(
            summary = "Стриминг видео по ID",
            description = "Возвращает InputStream для потокового воспроизведения видео по ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Видео найден и возвращен"),
            @ApiResponse(responseCode = "404", description = "Видео не найдено", content = @Content)
    })
    @GetMapping("/stream-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getById(
            @Parameter(description = "ID видео") @PathVariable("id") Long id) {

        String fileName = videoService.getById(id).getFileName();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @Operation(
            summary = "Скачать видео по имени файла",
            description = "Возвращает InputStream по имени файла"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Файл найден и возвращен"),
            @ApiResponse(responseCode = "404", description = "Файл не найден", content = @Content)
    })
    @GetMapping("/get-file-by-name/{file_name}")
    public ResponseEntity<InputStreamResource> getByFileName(
            @Parameter(description = "Имя файла") @PathVariable("file_name") String fileName) {

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @Operation(
            summary = "Получить DTO видео по ID",
            description = "Возвращает метаинформацию о видео по его ID"
    )
    @ApiResponse(responseCode = "200", description = "Метаинформация возвращена",
            content = @Content(schema = @Schema(implementation = VideoDto.class)))
    @GetMapping("/get-video-by-id/{id}")
    public ResponseEntity<VideoDto> getImagesById(
            @Parameter(description = "ID видео") @PathVariable("id") Long id) {

        return ResponseEntity.ok(VideoMapper.mapEntityToDto(videoService.getById(id)));
    }

    @Operation(
            summary = "Получить DTO видео по имени файла",
            description = "Возвращает метаинформацию о видео по имени файла"
    )
    @ApiResponse(responseCode = "200", description = "Метаинформация возвращена",
            content = @Content(schema = @Schema(implementation = VideoDto.class)))
    @GetMapping("/get-video-by-fileName/{file_name}")
    public ResponseEntity<VideoDto> getImagesByFileName(
            @Parameter(description = "Имя файла") @PathVariable("file_name") String fileName) {

        return ResponseEntity.ok(VideoMapper.mapEntityToDto(videoService.getByFileName(fileName)));
    }
}
