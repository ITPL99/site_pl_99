package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.TeacherDtoRequest;
import com.example.site_pl_99.dto.TeacherDtoRequestUpdate;
import com.example.site_pl_99.dto.TeacherDtoResponse;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.mapper.TeacherMapper;
import com.example.site_pl_99.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для управления преподавателями.
 * <p>
 * Предоставляет API для создания, поиска, обновления и удаления преподавателей.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@RestController
@RequestMapping("/api/teacher")
@SecurityRequirement(name = "bearerAuth")
@Tag(
        name = "Преподаватели",
        description = """
                API для управления преподавателями.
                
                **Возможности:**
                - Создание и обновление преподавателей
                - Поиск по ФИО, дате рождения, портфолио
                - Фильтрация по статусу активности
                - Управление датами трудоустройства/увольнения
                
                **Авторизация:** Все endpoints требуют Bearer токен
                """
)
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(
            summary = "Получить преподавателя по ФИО",
            description = "Возвращает преподавателя по полному имени (точное совпадение).",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Преподаватель найден",
                            content = @Content(schema = @Schema(implementation = TeacherDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Преподаватель не найден")
            }
    )
    @GetMapping("/get-by-full-name")
    public ResponseEntity<TeacherDtoResponse> findByFullName(
            @Parameter(
                    description = "Полное имя преподавателя",
                    example = "Иванов Иван Иванович",
                    required = true
            )
            @RequestParam String fullName) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.getFullName(fullName)));
    }

    @Operation(
            summary = "Поиск преподавателей по дате рождения",
            description = """
                    Возвращает список преподавателей с указанной датой рождения.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    **Пример:** 1980-05-21
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список преподавателей",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TeacherDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-date-berth")
    public ResponseEntity<List<TeacherDtoResponse>> findByDateBirth(
            @Parameter(
                    description = "Дата рождения",
                    example = "1980-05-21",
                    required = true
            )
            @RequestParam LocalDate DateBirth) {
        return ResponseEntity.ok(teacherService.getByDateBerth(DateBirth).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск преподавателей по имени (частичное совпадение)",
            description = """
                    Возвращает список преподавателей, содержащих подстроку в имени.
                    
                    **Тип поиска:** LIKE (частичное совпадение)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список преподавателей",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TeacherDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-search-by-name")
    public ResponseEntity<List<TeacherDtoResponse>> getByName(
            @Parameter(
                    description = "Имя или часть имени преподавателя",
                    example = "Иван",
                    required = true
            )
            @RequestParam String name) {
        return ResponseEntity.ok(teacherService.searchByName(name).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск преподавателей по статусу активности",
            description = """
                    Возвращает список преподавателей по статусу активности.
                    
                    **Доступные статусы:**
                    - **ACTIVE** - Активный преподаватель
                    - **DELETED** - Удаленный преподаватель
                    - **NEW** - Новый преподаватель
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список преподавателей",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TeacherDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-by-acive-status")
    public ResponseEntity<List<TeacherDtoResponse>> getByActive(
            @Parameter(
                    description = "Статус активности",
                    example = "ACTIVE",
                    required = true
            )
            @RequestParam Active active) {
        return ResponseEntity.ok(teacherService.getByStatusActive(active).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск преподавателей по кафедре (портфолио)",
            description = "Возвращает список преподавателей, работающих в указанной кафедре.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список преподавателей",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TeacherDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-portfolio")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeacherDtoResponse>> findByPortfolio(
            @Parameter(
                    description = "Название кафедры",
                    example = "Кафедра программирования",
                    required = true
            )
            @RequestParam String department) {
        return ResponseEntity.ok(teacherService.getByPortfolio(department).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск преподавателей по дате трудоустройства",
            description = """
                    Возвращает список преподавателей, устроенных в указанную дату.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список преподавателей",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TeacherDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-date-employment")
    public ResponseEntity<List<TeacherDtoResponse>> findByDateEmployment(
            @Parameter(
                    description = "Дата трудоустройства",
                    example = "2020-09-01",
                    required = true
            )
            @RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(teacherService.getByDateEmployment(dateEmployment).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск преподавателей по дате увольнения",
            description = """
                    Возвращает список преподавателей, уволенных в указанную дату.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список преподавателей",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TeacherDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-date-dismissal")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeacherDtoResponse>> findByDateDismissal(
            @Parameter(
                    description = "Дата увольнения",
                    example = "2023-06-30",
                    required = true
            )
            @RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(teacherService.getDateDismissal(dateDismissal).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(
            summary = "Получить всех преподавателей",
            description = "Возвращает список всех активных преподавателей.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех преподавателей",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TeacherDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all")
    public ResponseEntity<List<TeacherDtoResponse>> findAll() {
        return ResponseEntity.ok(teacherService.getAll().stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(
            summary = "Получить преподавателя по ID",
            description = "Возвращает полную информацию о преподавателе по его ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о преподавателе",
                            content = @Content(schema = @Schema(implementation = TeacherDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Преподаватель не найден")
            }
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<TeacherDtoResponse> findById(
            @Parameter(
                    description = "ID преподавателя",
                    example = "1",
                    required = true
            )
            @PathVariable Long id) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.getById(id)));
    }

    @Operation(
            summary = "Обновить данные преподавателя по ID",
            description = """
                    Обновляет данные преподавателя.
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные преподавателя обновлены",
                            content = @Content(schema = @Schema(implementation = TeacherDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "404", description = "Преподаватель не найден")
            }
    )
    @PutMapping("/update-by-id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeacherDtoResponse> updateById(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для обновления преподавателя",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TeacherDtoRequestUpdate.class))
            )
            @RequestBody TeacherDtoRequestUpdate teacherDtoRequest) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(
                teacherService.update(TeacherMapper.toEntityUpdate(teacherDtoRequest))
        ));
    }

    @Operation(
            summary = "Создать нового преподавателя",
            description = """
                    Создает и сохраняет нового преподавателя в системе.
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Преподаватель успешно создан",
                            content = @Content(schema = @Schema(implementation = TeacherDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные")
            }
    )
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeacherDtoResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные нового преподавателя",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TeacherDtoRequest.class))
            )
            @RequestBody TeacherDtoRequest teacherEntity) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(
                teacherService.save(TeacherMapper.toEntity(teacherEntity))
        ));
    }

    @Operation(
            summary = "Удалить преподавателя по ID",
            description = """
                    Удаляет преподавателя по идентификатору (soft delete).
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Преподаватель успешно удален"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "404", description = "Преподаватель не найден")
            }
    )
    @DeleteMapping("/delete-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(
            @Parameter(
                    description = "ID преподавателя для удаления",
                    example = "1",
                    required = true
            )
            @PathVariable Long id) {
        teacherService.deleteById(id);
    }
}
