package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.MasterDtoRequest;
import com.example.site_pl_99.dto.MasterDtoRequestUpdate;
import com.example.site_pl_99.dto.MasterDtoResponse;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.mapper.MasterMapper;
import com.example.site_pl_99.service.impl.MasterServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для управления мастерами.
 * <p>
 * Предоставляет API для создания, поиска, обновления и удаления мастеров.
 * Поддерживает мультиязычность для названий профессий.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@RestController
@RequestMapping("/api/master")
@SecurityRequirement(name = "bearerAuth")
@Tag(
        name = "Мастера",
        description = """
                API для управления мастерами.
                
                **Возможности:**
                - Создание и обновление мастеров
                - Поиск по ФИО, профессии
                - Фильтрация по датам трудоустройства/увольнения
                - Управление статусом активности
                
                **Авторизация:** Все endpoints требуют Bearer токен
                """
)
public class MasterController {

    private final MasterServiceImpl masterService;

    public MasterController(MasterServiceImpl masterService) {
        this.masterService = masterService;
    }

    @Operation(
            summary = "Получить мастера по ФИО",
            description = "Возвращает мастера по полному имени (точное совпадение).",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Мастер найден",
                            content = @Content(schema = @Schema(implementation = MasterDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Мастер не найден")
            }
    )
    @GetMapping("/get-by-full-name")
    public ResponseEntity<MasterDtoResponse> getMasterByFullName(
            @Parameter(
                    description = "Полное имя мастера",
                    example = "Сидоров Алексей Петрович",
                    required = true
            )
            @RequestParam String fullName) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.getFullName(fullName)));
    }

    @Operation(
            summary = "Поиск мастеров по имени (частичное совпадение)",
            description = """
                    Возвращает список мастеров, содержащих подстроку в имени.
                    
                    **Тип поиска:** LIKE (частичное совпадение)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список мастеров найден",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MasterDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-search-by-name")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByName(
            @Parameter(
                    description = "Имя или часть имени мастера",
                    example = "Алексей",
                    required = true
            )
            @RequestParam String name) {
        return ResponseEntity.ok(masterService.searchByName(name).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастеров по статусу активности",
            description = """
                    Возвращает список мастеров по статусу активности.
                    
                    **Доступные статусы:**
                    - **ACTIVE** - Активный мастер
                    - **DELETED** - Удаленный мастер
                    - **NEW** - Новый мастер
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список мастеров по статусу",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MasterDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-by-active-status")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByActiveStatus(
            @Parameter(
                    description = "Статус активности",
                    example = "ACTIVE",
                    required = true
            )
            @RequestParam Active status) {
        return ResponseEntity.ok(masterService.getAllActiveStatus(status).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастеров по дате рождения",
            description = """
                    Возвращает список мастеров с указанной датой рождения.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список мастеров по дате рождения",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MasterDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-date-berth")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByDateBerth(
            @Parameter(
                    description = "Дата рождения",
                    example = "1985-05-20",
                    required = true
            )
            @RequestParam LocalDate dateBerth) {
        return ResponseEntity.ok(masterService.getByDateBerth(dateBerth).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастеров по профессии",
            description = """
                    Возвращает список мастеров с указанной профессией.
                    
                    **Поиск выполняется по:**
                    - professionRu (русская профессия)
                    - professionKg (кыргызская профессия)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список мастеров по профессии",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MasterDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-profession")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByProfession(
            @Parameter(
                    description = "Профессия (RU или KG)",
                    example = "Сварщик",
                    required = true
            )
            @RequestParam String profession) {
        return ResponseEntity.ok(masterService.getByProfession(profession).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастеров по дате приема на работу",
            description = """
                    Возвращает список мастеров, устроенных в указанную дату.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список мастеров по дате приема",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MasterDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-date-employment")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByDateEmployment(
            @Parameter(
                    description = "Дата приема на работу",
                    example = "2020-05-10",
                    required = true
            )
            @RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(masterService.getByDateEmployment(dateEmployment).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастеров по дате увольнения",
            description = """
                    Возвращает список мастеров, уволенных в указанную дату.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список мастеров по дате увольнения",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MasterDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all-by-date-dismissal")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByDateDismissal(
            @Parameter(
                    description = "Дата увольнения",
                    example = "2023-09-15",
                    required = true
            )
            @RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(masterService.getByDateDismissal(dateDismissal).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастера по ID",
            description = "Возвращает полную информацию о мастере по его ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Мастер найден",
                            content = @Content(schema = @Schema(implementation = MasterDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Мастер не найден")
            }
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MasterDtoResponse> getMasterById(
            @Parameter(
                    description = "ID мастера",
                    example = "1",
                    required = true
            )
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.getById(id)));
    }

    @Operation(
            summary = "Получить всех мастеров",
            description = "Возвращает список всех активных мастеров.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех мастеров",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MasterDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all")
    public ResponseEntity<List<MasterDtoResponse>> getAll() {
        return ResponseEntity.ok(masterService.getAll().stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Создать нового мастера",
            description = """
                    Создает и сохраняет нового мастера в системе.
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Мастер успешно создан",
                            content = @Content(schema = @Schema(implementation = MasterDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные")
            }
    )
    @PostMapping("/save")
    public ResponseEntity<MasterDtoResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "DTO мастера для создания",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MasterDtoRequest.class))
            )
            @RequestBody MasterDtoRequest entity) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.save(MasterMapper.toEntity(entity))));
    }

    @Operation(
            summary = "Обновить данные мастера",
            description = """
                    Обновляет данные мастера.
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Мастер успешно обновлён",
                            content = @Content(schema = @Schema(implementation = MasterDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные"),
                    @ApiResponse(responseCode = "404", description = "Мастер не найден")
            }
    )
    @PutMapping("/update-master")
    public ResponseEntity<MasterDtoResponse> updateTeacher(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "DTO мастера для обновления",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MasterDtoRequestUpdate.class))
            )
            @RequestBody MasterDtoRequestUpdate entity) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.update(MasterMapper.toEntityUpdate(entity))));
    }

    @Operation(
            summary = "Удалить мастера по ID",
            description = """
                    Удаляет мастера по идентификатору (soft delete).
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Мастер удалён"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "404", description = "Мастер не найден")
            }
    )
    @DeleteMapping("/delete-by-id/{id}")
    public void delete(
            @Parameter(
                    description = "ID мастера для удаления",
                    example = "1",
                    required = true
            )
            @PathVariable("id") Long id) {
        masterService.deleteById(id);
    }
}
