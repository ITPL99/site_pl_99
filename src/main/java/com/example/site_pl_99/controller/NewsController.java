package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.NewsDtoPreviewResponse;
import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.dto.NewsDtoUpdate;
import com.example.site_pl_99.mapper.NewsMapper;
import com.example.site_pl_99.service.NewsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "News Controller", description = "Управление новостями: создание, получение, фильтрация, удаление")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Operation(
            summary = "Получить новость по заголовку",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новость найдена"
                    )
            }
    )
    @GetMapping("/get-by-title")
    public ResponseEntity<NewsDtoResponse> findByTitle(
            @Parameter(description = "Заголовок новости", example = "New Shop Opened") @RequestParam String title) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.getByTitle(title)));
    }

    @Operation(
            summary = "Получить новости по содержанию заголовка",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список новостей"
                    )
            }
    )
    @GetMapping("/get-all-by-content-title")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findByContentTitle(
            @Parameter(description = "Содержимое заголовка", example = "Discount") @RequestParam String contentTitle) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllByContentTitle(contentTitle)));
    }

    @Operation(
            summary = "Получить все новости (предпросмотр)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех новостей (предпросмотр)"
                    )
            }
    )
    @GetMapping("/get-all")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findAllNews() {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAll()));
    }

    @Operation(
            summary = "Получить все новости (полные)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех полных новостей"
                    )
            }
    )
    @GetMapping("/get-all-full")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findAllFullNews() {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllFull()));
    }

    @Operation(
            summary = "Получить новости по содержанию подзаголовка",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список новостей по подзаголовку"
                    )
            }
    )
    @GetMapping("/get-all-by-content-sub-title")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findByContentSubTitle(
            @Parameter(description = "Содержимое подзаголовка", example = "Grand Opening") @RequestParam String contentSubTitle) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllByContentSubTitle(contentSubTitle)));
    }

    @Operation(
            summary = "Получить новость по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новость найдена"
                    ),
                    @ApiResponse(responseCode = "404", description = "Новость не найдена")
            }
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<NewsDtoResponse> findById(
            @Parameter(description = "ID новости", example = "1") @PathVariable("id") Long id) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.getById(id)));
    }

    @Operation(
            summary = "Сохранить новость",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новость успешно сохранена"
                    ),
                    @ApiResponse(responseCode = "400", description = "Некорректный запрос")
            }
    )
    @PostMapping("/save")
    public ResponseEntity<NewsDtoResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные новости для создания",
                    required = true
            )
            @RequestBody NewsDtoRequest entity) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.addNews(NewsMapper.toNewsEntity(entity))));
    }

    @Operation(
            summary = "Удалить новость по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Новость удалена")
            }
    )
    @DeleteMapping("/delete-by-id")
    public void deleteById(
            @Parameter(description = "ID новости", example = "3") @RequestParam Long id) {
        newsService.deleteById(id);
    }

    @Operation(
            summary = "Получить новости по статусу активности",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список новостей по статусу"
                    )
            }
    )
    @GetMapping("/get-by-status")
    public ResponseEntity<List<NewsDtoPreviewResponse>> getByStatus(
            @Parameter(description = "Статус активности (например: ACTIVE, INACTIVE)", example = "ACTIVE") @RequestParam String status) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllByActiveStatus(status)));
    }

    @PutMapping("/update-by-id/{id}")
    public ResponseEntity<NewsDtoResponse> updateById(@PathVariable Long id, @RequestBody NewsDtoUpdate newsDtoUpdate) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.updateNews(id, NewsMapper.mapToNewsEntityFromUpdate(newsDtoUpdate))));
    }
}
