package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.entity.ImageNewsEntity;
import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.NewsMapper;
import com.example.site_pl_99.service.ImageNewsService;
import com.example.site_pl_99.service.NewsService;
import com.example.site_pl_99.service.VideoNewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Tag(name = "Контроллер новостей")
@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;
    private final ImageNewsService imageNewsService;
    private final VideoNewsService videoNewsService;

    @Autowired
    public NewsController(NewsService newsService, ImageNewsService imageNewsService, VideoNewsService videoNewsService) {
        this.newsService = newsService;
        this.imageNewsService = imageNewsService;
        this.videoNewsService = videoNewsService;
    }
    @Operation(summary = "Добавить новость",description = "Добавить новость с возможностью прикреплять файлы")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Новость сохранена"),
    @ApiResponse(responseCode = "400",description = "Некоректный запрос"),
    @ApiResponse(responseCode = "403", description = "Не достаточно прав для создания новостей")})
    @PostMapping("/add-news")
    public ResponseEntity<String> saveNews(@Parameter(description = "Данные новостей(Заголовок, описание) ")@ModelAttribute NewsDtoRequest newsDto, @Parameter(description = "Принимает список файлов(фотки и видео), можно несколько файлов") @RequestParam(required = false) List<MultipartFile> file) throws BaseException {
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            NewsEntity newsEntity = newsService.saveNews(newsDto, user);
            List<ImageNewsEntity> imageNewsEntityList = new ArrayList<>();
            if(file != null && !file.isEmpty()) {
                for (MultipartFile file1 : file) {
                    imageNewsEntityList.add(imageNewsService.upload(
                            file1,
                            newsEntity.getId(),
                            user
                    ));
                }
            }
            newsEntity.setImages(imageNewsEntityList);
            newsService.saveNews(newsEntity);
            return ResponseEntity.ok().body("новость сохранена");
    }
    @Operation(
            summary = "Получить новость по ID",
            description = "Возвращает объект NewsDtoResponse по ID новости.."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение новости"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос"),
            @ApiResponse(responseCode = "404", description = "Новость не найдена")})
    @GetMapping("/get-news-id/{id}")
    public ResponseEntity<?> getNewsId(@Parameter(description = "ID новости")@PathVariable Long id) throws BaseException {
            return ResponseEntity.ok().body(NewsMapper.toNewsDtoResponse(newsService.getNewsId(id)));
    }
    @Operation(summary = "Получить все новости",
    description = "Возвращает список всех новостей в виде объектов NewDtoResponse")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешное получение списка новостей")})
    @GetMapping("/get-all-news")
    public ResponseEntity<?> getAllNews() throws BaseException {
            return ResponseEntity.ok().body(NewsMapper.toNewsDtoResponseList(newsService.getAllNews()));
    }
    @Operation(
            summary = "загрузить фото к новости",
            description = "Позволяет загрузить одно или несколько изображений к существующей новости по её ID"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Фото успешно добавлен"),
            @ApiResponse(responseCode = "400",description = "Неверный формат или ввод"),
            @ApiResponse(responseCode = "404", description = "Новость не найдена"),
            @ApiResponse(responseCode = "413",description = "Размер изображения перевышает лимит(3 мб)")})
    @PostMapping("/upload-image-news/{newsId}")
    public ResponseEntity<?> uploadImages(@Parameter(description = "Список изображений (формат multipart/form-data)")@RequestParam("file") List<MultipartFile> file, @Parameter(description = "ID новости, к которой загружаются изображения")@PathVariable Long newsId) throws BaseException {
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            for (MultipartFile file1 : file) {
                imageNewsService.upload(
                        file1,
                        newsId,
                        user
                );
            }
            return ResponseEntity.ok().build();
    }
    @Operation(
            summary = "Получить изображение по ID",
            description = "Возвращает файл изображения по его ID. Ответ имеет (image/jpeg)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение успешно получено",
                    content = @Content(mediaType = "image/*")
            ),
            @ApiResponse(responseCode = "404", description = "Изображение не найдено"),
            @ApiResponse(responseCode = "400", description = "Неверный тип файла"),})
    @GetMapping("/get-images-news/{id}")
    public ResponseEntity<?> streamImages(@Parameter(description = "ID изображения")@PathVariable Long id) throws BaseException {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageNewsService.getContentType(id)))
                .body(new InputStreamResource(imageNewsService.streamFile(id)));
    }
    @Operation(
            summary = "Загрузить видео к новости",
            description = "Позволяет загрузить один видеофайл (формат multipart/form-data) к существующей новости по её ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Видео успешно загружено"),
            @ApiResponse(responseCode = "400", description = "Ошибка запроса или неподдерживаемый формат файла"),
            @ApiResponse(responseCode = "413", description = "Видео превышает лимит(300 мб)"),
            @ApiResponse(responseCode = "403",description = "Недостаточно прав")})
    @PostMapping("/upload-video-news/{newsId}")
    public ResponseEntity<?> uploadVideo(@Parameter(description = "Видео для загрузки(формат multipart/form-data)")@RequestParam("file") MultipartFile file, @Parameter(description = "ID новости к которому надо прикрепить видео")@PathVariable Long newsId) throws BaseException{
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            videoNewsService.upload(
                    file,
                    newsId,
                    user
            );
            return ResponseEntity.ok().build();
    }
    @Operation( summary = "Получить видео по ID",
            description = "Возвращает поток видеофайла по его ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "видео успешно получено", content = @Content(mediaType = "video/*")),

    @ApiResponse(responseCode = "404", description = "Видео не найдено"),
    @ApiResponse(responseCode = "400", description = "Некоректный ввод Id")})
    @GetMapping("/get-video-news/{id}")
    public ResponseEntity<?> streamVideo(@Parameter(description = "ID видео")@PathVariable Long id) throws BaseException{
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(videoNewsService.getContentType(id)))
                .body(new InputStreamResource(videoNewsService.stream(id)));
    }
}
