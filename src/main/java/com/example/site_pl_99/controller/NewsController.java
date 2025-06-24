package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.mapper.NewsMapper;
import com.example.site_pl_99.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Новости", description = "Операции для управления новостями (поиск, сохранение, удаление)")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Operation(summary = "Найти новость по заголовку")
    @GetMapping("/get-by-title")
    public ResponseEntity<?> findByTitle(
            @RequestParam @Parameter(description = "Заголовок новости") String title) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.getTitle(title)));
    }

    @Operation(summary = "Найти все новости по содержимому заголовка")
    @GetMapping("/get-all-by-content-title")
    public ResponseEntity<?> findByContentTitle(
            @RequestParam @Parameter(description = "Подстрока в заголовке") String contentTitle) {
        return ResponseEntity.ok(
                newsService.getAllContentTitle(contentTitle)
                        .stream().map(NewsMapper::toNewsDtoResponse).toList()
        );
    }

    @Operation(summary = "Найти все новости по содержимому подзаголовка")
    @GetMapping("/get-all-by-content-sub-title")
    public ResponseEntity<?> findByContentSubTitle(
            @RequestParam @Parameter(description = "Подстрока в подзаголовке") String contentSubTitle) {
        return ResponseEntity.ok(
                newsService.getAllContentSubTitle(contentSubTitle)
                        .stream().map(NewsMapper::toNewsDtoResponse).toList()
        );
    }

    @Operation(summary = "Найти новость по ID")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> findById(
            @PathVariable("id") @Parameter(description = "ID новости") Long id) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.getById(id)));
    }

    @Operation(
            summary = "Создать или обновить новость",
            requestBody = @RequestBody(
                    description = "Новость для сохранения",
                    required = true,
                    content = @Content(schema = @Schema(implementation = NewsDtoRequest.class))
            )
    )
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody NewsDtoRequest entity) {
        return ResponseEntity.ok(
                NewsMapper.toNewsDtoResponse(newsService.save(NewsMapper.toNewsEntity(entity)))
        );
    }

    @Operation(summary = "Удалить новость по ID")
    @DeleteMapping("/delete-by-id")
    public void deleteById(
            @RequestParam @Parameter(description = "ID новости") Long id) {
        newsService.deleteById(id);
    }
}
