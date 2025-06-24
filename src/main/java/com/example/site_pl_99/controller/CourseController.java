package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.enums.CourseType;
import com.example.site_pl_99.mapper.CourseMapper;
import com.example.site_pl_99.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/course")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Курсы", description = "Операции для управления курсами")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Получить курс по названию")
    @GetMapping("/get-by-title")
    public ResponseEntity<?> getByTitle(
            @RequestParam @Parameter(description = "Название курса") String title) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.getByTitle(title)));
    }

    @Operation(summary = "Получить все курсы по типу")
    @GetMapping("/get-all-by-type")
    public ResponseEntity<List<?>> getAllCourseByType(
            @RequestParam @Parameter(description = "Тип курса") CourseType type) {
        return ResponseEntity.ok(
                courseService.getAllCourseByType(type).stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
    }

    @Operation(summary = "Получить все курсы по цене")
    @GetMapping("/get-all-by-price")
    public ResponseEntity<List<?>> getAllCourseByPrice(
            @RequestParam @Parameter(description = "Цена курса") Double price) {
        return ResponseEntity.ok(
                courseService.getAllCourseByPrice(price).stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
    }

    @Operation(summary = "Получить все курсы по дате начала")
    @GetMapping("/get-all-by-date-start")
    public ResponseEntity<List<?>> getAllCourseByDateStart(
            @RequestParam @Parameter(description = "Дата начала курса (формат: yyyy-MM-dd)") LocalDate dateStart) {
        return ResponseEntity.ok(
                courseService.getAllCourseByDateStart(dateStart).stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
    }

    @Operation(summary = "Получить все курсы по дате окончания")
    @GetMapping("/get-all-by-date-end")
    public ResponseEntity<List<?>> getAllCourseByDateEnd(
            @RequestParam @Parameter(description = "Дата окончания курса (формат: yyyy-MM-dd)") LocalDate dateEnd) {
        return ResponseEntity.ok(
                courseService.getAllCourseByDateEnd(dateEnd).stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
    }

    @Operation(
            summary = "Получить курс по ID",
            description = "Возвращает курс по его уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Курс найден",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.example.site_pl_99.dto.CourseDtoResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Курс не найден")
            }
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(
            @PathVariable("id") @Parameter(description = "ID курса") Long id) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.getById(id)));
    }

    @Operation(summary = "Сохранить новый курс")
    @ApiResponse(responseCode = "200", description = "Курс успешно сохранён")
    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody @Parameter(description = "DTO запроса курса", required = true)
            CourseDtoRequest entity) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.save(CourseMapper.toEntity(entity))));
    }

    @Operation(summary = "Получить список всех курсов")
    @GetMapping("/get-all")
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(courseService.getAll().stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
    }

    @Operation(summary = "Удалить курс по ID")
    @ApiResponse(responseCode = "200", description = "Курс успешно удалён")
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(
            @PathVariable("id") @Parameter(description = "ID курса") Long id) {
        courseService.deleteById(id);
    }
}
