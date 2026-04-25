package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.dto.CourseDtoResponse;
import com.example.site_pl_99.dto.CoursePreviewDto;
import com.example.site_pl_99.enums.CourseType;
import com.example.site_pl_99.mapper.CourseMapper;
import com.example.site_pl_99.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для управления курсами.
 * <p>
 * Предоставляет API для создания, поиска, получения и удаления курсов.
 * Поддерживает мультиязычность (русский и кыргызский) для названий и описаний.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@RestController
@RequestMapping("/api/course")
@SecurityRequirement(name = "bearerAuth")
@Tag(
        name = "Курсы",
        description = """
                API для управления образовательными курсами.
                
                **Возможности:**
                - Создание новых курсов с мультиязычными названиями и описаниями
                - Поиск курсов по заголовку, типу, цене, датам
                - Получение списка всех курсов
                - Удаление курсов
                
                **Типы курсов:** PROGRAMMING, DESIGN, MARKETING
                **Статусы:** ACTIVE, NEW, DELETED
                
                **Авторизация:** Все endpoints требуют Bearer токен (кроме публичных)
                """
)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(
            summary = "Поиск курсов по заголовку",
            description = """
                    Возвращает список курсов, заголовок которых содержит указанную строку.
                    
                    **Поиск выполняется:**
                    - По заголовку на русском языке (titleRu)
                    - По заголовку на кыргызском языке (titleKg)
                    
                    **Пример:** Поиск "Java" найдет курсы:
                    - "Курс по обучению Java"
                    - "Java Programming Basics"
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список курсов успешно получен",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CourseDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "400", description = "Пустой параметр поиска")
            }
    )
    @GetMapping("/get-by-title")
    public ResponseEntity<List<CourseDtoResponse>> getByTitle(
            @Parameter(
                    description = "Строка для поиска в заголовке курса",
                    example = "Python",
                    required = true
            )
            @RequestParam String title) {
        return ResponseEntity.ok(courseService.getByTitle(title).stream()
                .map(CourseMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Получить курсы по типу",
            description = """
                    Возвращает список курсов указанного типа.
                    
                    **Доступные типы:**
                    - **PROGRAMMING** - Программирование и IT
                    - **DESIGN** - Дизайн и UX/UI
                    - **MARKETING** - Маркетинг и Digital
                    
                    **Формат ответа:** Список CoursePreviewDto (краткая информация)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список курсов по типу",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CoursePreviewDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-type")
    public ResponseEntity<List<CoursePreviewDto>> getAllCourseByType(
            @Parameter(
                    description = "Тип курса",
                    example = "PROGRAMMING",
                    required = true
            )
            @RequestParam CourseType type) {
        return ResponseEntity.ok(courseService.getAllCourseByType(type).stream()
                .map(CourseMapper::mapToPreviewEntity)
                .toList());
    }

    @Operation(
            summary = "Поиск курсов по цене",
            description = "Возвращает список курсов с указанной ценой (точное совпадение).",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список курсов с указанной ценой",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CoursePreviewDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-price")
    public ResponseEntity<List<CoursePreviewDto>> getAllCourseByPrice(
            @Parameter(
                    description = "Цена курса (в сомах)",
                    example = "15000.00",
                    required = true
            )
            @RequestParam Double price) {
        return ResponseEntity.ok(courseService.getAllCourseByPrice(price).stream()
                .map(CourseMapper::mapToPreviewEntity)
                .toList());
    }

    @Operation(
            summary = "Поиск курсов по дате начала",
            description = """
                    Возвращает список курсов, начинающихся в указанную дату.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    **Пример:** 2025-07-01
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список курсов",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CoursePreviewDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-date-start")
    public ResponseEntity<List<CoursePreviewDto>> getAllCourseByDateStart(
            @Parameter(
                    description = "Дата начала курса",
                    example = "2025-07-01",
                    required = true
            )
            @RequestParam LocalDate dateStart) {
        return ResponseEntity.ok(courseService.getAllCourseByDateStart(dateStart).stream()
                .map(CourseMapper::mapToPreviewEntity)
                .toList());
    }

    @Operation(
            summary = "Поиск курсов по дате окончания",
            description = """
                    Возвращает список курсов, заканчивающихся в указанную дату.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    **Пример:** 2025-08-01
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список курсов",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CoursePreviewDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-date-end")
    public ResponseEntity<List<CoursePreviewDto>> getAllCourseByDateEnd(
            @Parameter(
                    description = "Дата окончания курса",
                    example = "2025-08-01",
                    required = true
            )
            @RequestParam LocalDate dateEnd) {
        return ResponseEntity.ok(courseService.getAllCourseByDateEnd(dateEnd).stream()
                .map(CourseMapper::mapToPreviewEntity)
                .toList());
    }

    @Operation(
            summary = "Получить курс по ID",
            description = """
                    Возвращает полную информацию о курсе по его идентификатору.
                    
                    **Включает:**
                    - Полные названия (RU/KG)
                    - Полные описания (RU/KG)
                    - Цену, даты, изображение
                    - Статус активности
                    
                    **Ошибки:**
                    - 404 - Курс не найден
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о курсе",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CourseDtoResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Курс не найден")
            }
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<CourseDtoResponse> getById(
            @Parameter(
                    description = "ID курса",
                    example = "1",
                    required = true
            )
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.getById(id)));
    }

    @Operation(
            summary = "Создать новый курс",
            description = """
                    Создает и сохраняет новый курс в системе.
                    
                    **Обязательные поля:**
                    - type - тип курса
                    - titleRu/titleKg - название
                    - descriptionRu/descriptionKg - описание
                    - price - цена
                    - dateStart/dateEnd - даты проведения
                    
                    **Пример запроса:**
                    ```json
                    {
                      "courseType": "PROGRAMMING",
                      "titleRu": "Курс по Python",
                      "titleKg": "Python боюнча курс",
                      "descriptionRu": "Базовый курс по Python",
                      "descriptionKg": "Python боюнча базалык курс",
                      "price": 15000.00,
                      "dateStart": "2025-07-01",
                      "dateEnd": "2025-08-01"
                    }
                    ```
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Курс успешно создан",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CourseDtoResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)")
            }
    )
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDtoResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные курса для создания",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CourseDtoRequest.class))
            )
            @RequestBody CourseDtoRequest entity) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.save(CourseMapper.toEntity(entity))));
    }

    @Operation(
            summary = "Получить все активные курсы",
            description = """
                    Возвращает список всех курсов со статусом ACTIVE и NEW.
                    
                    **Исключает:** Курсы со статусом DELETED
                    **Формат:** Список CoursePreviewDto (краткая информация)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех активных курсов",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CoursePreviewDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all")
    public ResponseEntity<List<CoursePreviewDto>> getAll() {
        return ResponseEntity.ok(CourseMapper.mapListEntityToPreviewDtoList(courseService.getAll()));
    }

    @Operation(
            summary = "Получить все курсы (включая удаленные)",
            description = """
                    Возвращает список ВСЕХ курсов без фильтрации по статусу.
                    
                    **Включает:** ACTIVE, NEW, DELETED
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех курсов",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CoursePreviewDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)")
            }
    )
    @GetMapping("/get-all-full")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CoursePreviewDto>> getAllFull() {
        return ResponseEntity.ok(CourseMapper.mapListEntityToPreviewDtoList(courseService.getAllFull()));
    }

    @Operation(
            summary = "Удалить курс по ID",
            description = """
                    Удаляет курс по его идентификатору (устанавливает статус DELETED).
                    
                    **Примечание:** Курс не удаляется физически из БД,
                    а помечается как удаленный (soft delete).
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Курс успешно удален"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "404", description = "Курс не найден")
            }
    )
    @DeleteMapping("/delete-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(
            @Parameter(
                    description = "ID курса для удаления",
                    example = "1",
                    required = true
            )
            @PathVariable("id") Long id) {
        courseService.deleteById(id);
    }
}
