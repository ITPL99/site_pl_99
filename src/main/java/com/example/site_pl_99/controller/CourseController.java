package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.dto.CourseDtoResponse;
import com.example.site_pl_99.dto.CoursePreviewDto;
import com.example.site_pl_99.enums.CourseType;
import com.example.site_pl_99.mapper.CourseMapper;
import com.example.site_pl_99.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/course")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Course Controller", description = "Управление курсами: создание, поиск, удаление и просмотр")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Получить курсы по заголовку",
            description = "Возвращает список курсов, содержащих указанный заголовок.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получены курсы")
            })
    @GetMapping("/get-by-title")
    public ResponseEntity<List<CourseDtoResponse>> getByTitle(
            @Parameter(description = "Заголовок курса для поиска", required = true)
            @RequestParam String title) {
        return ResponseEntity.ok(courseService.getByTitle(title).stream()
                .map(CourseMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(summary = "Получить все курсы по типу",
            description = "Возвращает список превью курсов по указанному типу.")
    @GetMapping("/get-all-by-type")
    public ResponseEntity<List<CoursePreviewDto>> getAllCourseByType(
            @Parameter(description = "Тип курса", required = true)
            @RequestParam CourseType type) {
        return ResponseEntity.ok(courseService.getAllCourseByType(type).stream()
                .map(CourseMapper::mapToPreviewEntity)
                .toList());
    }

    @Operation(summary = "Получить все курсы по цене",
            description = "Возвращает список превью курсов с заданной ценой.")
    @GetMapping("/get-all-by-price")
    public ResponseEntity<List<CoursePreviewDto>> getAllCourseByPrice(
            @Parameter(description = "Цена курса", required = true)
            @RequestParam Double price) {
        return ResponseEntity.ok(courseService.getAllCourseByPrice(price).stream()
                .map(CourseMapper::mapToPreviewEntity)
                .toList());
    }

    @Operation(summary = "Получить все курсы по дате начала",
            description = "Возвращает список превью курсов, начинающихся с указанной даты.")
    @GetMapping("/get-all-by-date-start")
    public ResponseEntity<List<CoursePreviewDto>> getAllCourseByDateStart(
            @Parameter(description = "Дата начала курса (формат: YYYY-MM-DD)", required = true)
            @RequestParam LocalDate dateStart) {
        return ResponseEntity.ok(courseService.getAllCourseByDateStart(dateStart).stream()
                .map(CourseMapper::mapToPreviewEntity)
                .toList());
    }

    @Operation(summary = "Получить все курсы по дате окончания",
            description = "Возвращает список превью курсов, заканчивающихся в указанную дату.")
    @GetMapping("/get-all-by-date-end")
    public ResponseEntity<List<CoursePreviewDto>> getAllCourseByDateEnd(
            @Parameter(description = "Дата окончания курса (формат: YYYY-MM-DD)", required = true)
            @RequestParam LocalDate dateEnd) {
        return ResponseEntity.ok(courseService.getAllCourseByDateEnd(dateEnd).stream()
                .map(CourseMapper::mapToPreviewEntity)
                .toList());
    }

    @Operation(summary = "Получить курс по ID",
            description = "Возвращает полную информацию о курсе по его идентификатору.")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<CourseDtoResponse> getById(
            @Parameter(description = "ID курса", required = true)
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.getById(id)));
    }

    @Operation(summary = "Создать новый курс",
            description = "Создает и сохраняет новый курс в системе.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Курс успешно создан")
            })
    @PostMapping("/save")
    public ResponseEntity<CourseDtoResponse> save(
            @Parameter(description = "Данные курса для создания", required = true)
            @RequestBody CourseDtoRequest entity) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.save(CourseMapper.toEntity(entity))));
    }

    @Operation(summary = "Получить все курсы",
            description = "Возвращает список всех курсов (в виде превью).")
    @GetMapping("/get-all")
    public ResponseEntity<List<CoursePreviewDto>> getAll() {
        return ResponseEntity.ok(courseService.getAll().stream().map(CourseMapper::mapToPreviewEntity).toList());
    }

    @Operation(summary = "Удалить курс по ID",
            description = "Удаляет курс по его идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Курс успешно удален")
            })
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(
            @Parameter(description = "ID курса для удаления", required = true)
            @PathVariable("id") Long id) {
        courseService.deleteById(id);
    }
}
