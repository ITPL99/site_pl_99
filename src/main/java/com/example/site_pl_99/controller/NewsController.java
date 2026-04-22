package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.NewsDtoPreviewResponse;
import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.dto.NewsDtoUpdate;
import com.example.site_pl_99.mapper.NewsMapper;
import com.example.site_pl_99.service.NewsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления новостями.
 * <p>
 * Предоставляет API для создания, получения, обновления и удаления новостей.
 * Поддерживает мультиязычность (русский и кыргызский) для заголовков и описаний.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/news")
@SecurityRequirement(name = "bearerAuth")
@Tag(
        name = "Новости",
        description = """
                API для управления новостями и публикациями.
                
                **Возможности:**
                - Создание новостей с мультиязычным контентом
                - Поиск по заголовкам и подзаголовкам
                - Фильтрация по статусу активности
                - Управление изображениями и видео
                
                **Мультиязычность:**
                - Заголовки на RU и KG
                - Подзаголовки на RU и KG
                - Описания на RU и KG
                
                **Авторизация:** Все endpoints требуют Bearer токен
                """
)
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Operation(
            summary = "Получить новость по заголовку",
            description = """
                    Возвращает новость по точному совпадению заголовка.
                    
                    **Поиск выполняется по:**
                    - titleRu (русский заголовок)
                    - titleKg (кыргызский заголовок)
                    
                    **Ошибки:**
                    - 404 - Новость не найдена
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новость найдена",
                            content = @Content(schema = @Schema(implementation = NewsDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Новость не найдена")
            }
    )
    @GetMapping("/get-by-title")
    public ResponseEntity<NewsDtoResponse> findByTitle(
            @Parameter(
                    description = "Заголовок новости",
                    example = "Открытие нового курса",
                    required = true
            )
            @RequestParam String title) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.getByTitle(title)));
    }

    @Operation(
            summary = "Поиск новостей по части заголовка",
            description = """
                    Возвращает список новостей, содержащих подстроку в заголовке.
                    
                    **Тип поиска:** LIKE (частичное совпадение)
                    **Пример:** Поиск "курс" найдет:
                    - "Открытие нового курса"
                    - "Курсы по программированию"
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список новостей",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = NewsDtoPreviewResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-content-title")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findByContentTitle(
            @Parameter(
                    description = "Часть заголовка для поиска",
                    example = "курс",
                    required = true
            )
            @RequestParam String contentTitle) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllByContentTitle(contentTitle)));
    }

    @Operation(
            summary = "Получить все новости (предпросмотр)",
            description = """
                    Возвращает список всех новостей в формате предпросмотра.
                    
                    **Включает:**
                    - Краткое название (заголовок)
                    - Миниатюрное изображение
                    - Дату создания
                    
                    **Исключает:** Полные описания (для экономии трафика)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех новостей (предпросмотр)",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = NewsDtoPreviewResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findAllNews() {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAll()));
    }

    @Operation(
            summary = "Получить все новости (полные)",
            description = """
                    Возвращает список всех новостей без фильтрации по статусу.
                    
                    **Требуемая роль:** ADMIN
                    
                    **Включает:**
                    - Все статусы (ACTIVE, INACTIVE, DELETED)
                    - Полные данные новостей
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех полных новостей",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = NewsDtoPreviewResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)")
            }
    )
    @GetMapping("/get-all-full")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findAllFullNews() {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllFull()));
    }

    @Operation(
            summary = "Поиск новостей по части подзаголовка",
            description = """
                    Возвращает список новостей, содержащих подстроку в подзаголовке.
                    
                    **Поиск выполняется по:**
                    - subTitleRu (русский подзаголовок)
                    - subTitleKg (кыргызский подзаголовок)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список новостей по подзаголовку",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = NewsDtoPreviewResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-content-sub-title")
    public ResponseEntity<List<NewsDtoPreviewResponse>> findByContentSubTitle(
            @Parameter(
                    description = "Часть подзаголовка для поиска",
                    example = "открытие",
                    required = true
            )
            @RequestParam String contentSubTitle) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllByContentSubTitle(contentSubTitle)));
    }

    @Operation(
            summary = "Получить новость по ID",
            description = """
                    Возвращает полную информацию о новости по ее ID.
                    
                    **Включает:**
                    - Полные заголовки (RU/KG)
                    - Полные подзаголовки (RU/KG)
                    - Полные описания (RU/KG)
                    - Изображения и видео
                    - Дату создания и статус
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новость найдена",
                            content = @Content(schema = @Schema(implementation = NewsDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Новость не найдена")
            }
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<NewsDtoResponse> findById(
            @Parameter(
                    description = "ID новости",
                    example = "1",
                    required = true
            )
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.getById(id)));
    }

    @Operation(
            summary = "Создать новость",
            description = """
                    Создает и сохраняет новую новость в системе.
                    
                    **Обязательные поля:**
                    - titleRu/titleKg - Заголовок
                    - subTitleRu/subTitleKg - Подзаголовок
                    - descriptionRu/descriptionKg - Описание
                    
                    **Опциональные поля:**
                    - imageSmall/imageFull - Изображения
                    - VideoFileName - Видео
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новость успешно сохранена",
                            content = @Content(schema = @Schema(implementation = NewsDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "400", description = "Некорректный запрос")
            }
    )
    @PostMapping("/save")
    public ResponseEntity<NewsDtoResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные новости для создания",
                    required = true,
                    content = @Content(schema = @Schema(implementation = NewsDtoRequest.class))
            )
            @RequestBody NewsDtoRequest entity) {
        log.info("Пришло {}",entity);
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.addNews(NewsMapper.toNewsEntity(entity))));
    }

    @Operation(
            summary = "Удалить новость по ID",
            description = """
                    Удаляет новость по ее идентификатору (soft delete).
                    
                    **Примечание:**
                    - Новость не удаляется физически
                    - Устанавливается статус DELETED
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Новость удалена"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "404", description = "Новость не найдена")
            }
    )
    @DeleteMapping("/delete-by-id")
    public void deleteById(
            @Parameter(
                    description = "ID новости для удаления",
                    example = "1",
                    required = true
            )
            @RequestParam Long id) {
        newsService.deleteById(id);
    }

    @Operation(
            summary = "Получить новости по статусу",
            description = """
                    Возвращает список новостей по указанному статусу активности.
                    
                    **Доступные статусы:**
                    - **ACTIVE** - Активные новости (отображаются на сайте)
                    - **INACTIVE** - Неактивные новости
                    - **DELETED** - Удаленные новости
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список новостей по статусу",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = NewsDtoPreviewResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-by-status")
    public ResponseEntity<List<NewsDtoPreviewResponse>> getByStatus(
            @Parameter(
                    description = "Статус активности",
                    example = "ACTIVE",
                    required = true
            )
            @RequestParam String status) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoPreviewResponseList(newsService.getAllByActiveStatus(status)));
    }

    @Operation(
            summary = "Обновить новость по ID",
            description = """
                    Обновляет данные новости по ее идентификатору.
                    
                    **Примечание:**
                    - Передавайте только изменяемые поля
                    - null поля не обновляются
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новость успешно обновлена",
                            content = @Content(schema = @Schema(implementation = NewsDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "404", description = "Новость не найдена")
            }
    )
    @PutMapping("/update-by-id/{id}")
    public ResponseEntity<NewsDtoResponse> updateById(
            @Parameter(
                    description = "ID новости для обновления",
                    example = "1",
                    required = true
            )
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для обновления новости",
                    required = true,
                    content = @Content(schema = @Schema(implementation = NewsDtoUpdate.class))
            )
            @RequestBody NewsDtoUpdate newsDtoUpdate) {
        return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsService.updateNews(id, NewsMapper.mapToNewsEntityFromUpdate(newsDtoUpdate))));
    }
}
