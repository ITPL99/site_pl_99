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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Контроллер для управления изображениями.
 * <p>
 * Предоставляет API для загрузки, получения и управления изображениями.
 * Изображения хранятся в MinIO, метаданные - в базе данных.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@RestController
@RequestMapping("/api/images")
@SecurityRequirement(name = "bearerAuth")
@Tag(
        name = "Изображения",
        description = """
                API для загрузки, получения и управления изображениями.
                
                **Возможности:**
                - Загрузка изображений в MinIO
                - Получение файлов изображений по ID или имени
                - Получение метаданных изображений
                
                **Хранение:**
                - Файлы хранятся в MinIO
                - Метаданные (ID, имя файла) - в БД
                
                **Авторизация:** Все endpoints требуют Bearer токен
                """
)
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
            description = """
                    Сохраняет изображение в MinIO и сохраняет метаданные в базе данных.
                    
                    **Поддерживаемые форматы:** JPG, PNG, GIF, WebP
                    **Максимальный размер:** 100MB
                    
                    **Процесс:**
                    1. Загрузка файла в MinIO
                    2. Сохранение метаданных (ID, имя файла) в БД
                    
                    **Требуемая роль:** ADMIN
                    """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл успешно загружен",
                            content = @Content(schema = @Schema(implementation = ImageDto.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "400", description = "Некорректный файл или превышен размер")
            }
    )
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ImageDto> save(
            @Parameter(
                    description = "Файл изображения (JPG, PNG, GIF, WebP, max 100MB)",
                    required = true
            )
            @RequestParam("image") MultipartFile image) {
        minIoService.save(image);
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(image.getOriginalFilename());
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.save(imageEntity)));
    }

    @Operation(
            summary = "Получить файл изображения по ID",
            description = """
                    Возвращает бинарный файл изображения из MinIO по ID.
                    
                    **Ответ:** Content-Type соответствует типу изображения
                    **Использование:** Вставка в img src или скачивание
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл найден",
                            content = @Content(mediaType = "image/*")
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Изображение не найдено")
            }
    )
    @GetMapping("/get-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getById(
            @Parameter(
                    description = "ID изображения",
                    example = "1",
                    required = true
            )
            @PathVariable("id") Long id) {
        String fileName = imageService.getById(id).getFileName();
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @Operation(
            summary = "Получить файл изображения по имени файла",
            description = """
                    Возвращает бинарный файл изображения по имени файла из MinIO.
                    
                    **Ответ:** Content-Type соответствует типу изображения
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл получен",
                            content = @Content(mediaType = "image/*")
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Файл не найден")
            }
    )
    @GetMapping("/get-file-by-name/{file_name}")
    public ResponseEntity<InputStreamResource> getByFileName(
            @Parameter(
                    description = "Имя файла изображения",
                    example = "photo_2024.jpg",
                    required = true
            )
            @PathVariable("file_name") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(minIoService.getContentType(fileName)))
                .body(new InputStreamResource(minIoService.getByFileName(fileName)));
    }

    @Operation(
            summary = "Получить метаданные изображения по ID",
            description = """
                    Возвращает метаданные изображения (ID, имя файла) по его ID.
                    
                    **Не возвращает сам файл!** Для получения файла используйте /get-file-by-id/{id}
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Метаданные изображения получены",
                            content = @Content(schema = @Schema(implementation = ImageDto.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Изображение не найдено")
            }
    )
    @GetMapping("/get-images-by-id/{id}")
    public ResponseEntity<ImageDto> getImagesById(
            @Parameter(
                    description = "ID изображения",
                    example = "1",
                    required = true
            )
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.getById(id)));
    }

    @Operation(
            summary = "Получить метаданные изображения по имени файла",
            description = """
                    Возвращает метаданные изображения (ID, имя файла) по имени файла.
                    
                    **Не возвращает сам файл!** Для получения файла используйте /get-file-by-name/{file_name}
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Метаданные изображения получены",
                            content = @Content(schema = @Schema(implementation = ImageDto.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Файл не найден")
            }
    )
    @GetMapping("/get-images-by-fileName/{file_name}")
    public ResponseEntity<ImageDto> getImagesByFileName(
            @Parameter(
                    description = "Имя файла изображения",
                    example = "photo_2024.jpg",
                    required = true
            )
            @PathVariable("file_name") String fileName) {
        return ResponseEntity.ok(ImageMapper.mapEntityToDto(imageService.getByFileName(fileName)));
    }
}
