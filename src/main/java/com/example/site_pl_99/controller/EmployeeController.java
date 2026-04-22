package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.EmployeeDtoRequest;
import com.example.site_pl_99.dto.EmployeeDtoRequestUpdate;
import com.example.site_pl_99.dto.EmployeeDtoResponse;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.mapper.EmployeeMapper;
import com.example.site_pl_99.service.EmployeeService;

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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для управления сотрудниками.
 * <p>
 * Предоставляет API для создания, поиска, обновления и удаления сотрудников.
 * Поддерживает мультиязычность для названий отделов (русский и кыргызский).
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@RestController
@RequestMapping("/api/employee")
@SecurityRequirement(name = "bearerAuth")
@Tag(
        name = "Сотрудники",
        description = """
                API для управления сотрудниками организации.
                
                **Возможности:**
                - Создание и обновление сотрудников
                - Поиск по ФИО, дате рождения, отделу
                - Фильтрация по датам трудоустройства/увольнения
                - Управление статусом активности
                
                **Авторизация:** Все endpoints требуют Bearer токен
                """
)
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @Operation(
            summary = "Получить сотрудника по ФИО",
            description = """
                    Возвращает сотрудника по полному имени (точное совпадение).
                    
                    **Поиск выполняется по полю:** fullName
                    **Регистр:** не чувствителен к регистру
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сотрудник найден",
                            content = @Content(schema = @Schema(implementation = EmployeeDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
            }
    )
    @GetMapping("/get-by-full-name")
    public ResponseEntity<EmployeeDtoResponse> getFullName(
            @Parameter(
                    description = "Полное имя сотрудника",
                    example = "Иванов Иван Иванович",
                    required = true
            )
            @RequestParam String fullName) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.getFullName(fullName)));
    }

    @Operation(
            summary = "Поиск сотрудников по части имени",
            description = """
                    Возвращает список сотрудников, содержащих подстроку в имени.
                    
                    **Тип поиска:** LIKE (частичное совпадение)
                    **Пример:** Поиск "Иван" найдет:
                    - "Иванов Иван Иванович"
                    - "Иваненко Петр Сергеевич"
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список сотрудников",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/search-by-Name")
    public ResponseEntity<List<EmployeeDtoResponse>> getContentName(
            @Parameter(
                    description = "Часть имени сотрудника",
                    example = "Иван",
                    required = true
            )
            @RequestParam String fullName) {
        return ResponseEntity.ok(employeeService.searchByName(fullName).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск по дате рождения",
            description = """
                    Возвращает список сотрудников с указанной датой рождения.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    **Пример:** 1985-05-20
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список сотрудников",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-by-date-date-berth")
    public ResponseEntity<List<EmployeeDtoResponse>> getByDateBerth(
            @Parameter(
                    description = "Дата рождения",
                    example = "1985-05-20",
                    required = true
            )
            @RequestParam LocalDate dateBerth) {
        return ResponseEntity.ok(employeeService.getByDateBerth(dateBerth).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск по статусу активности",
            description = """
                    Возвращает список сотрудников по статусу активности.
                    
                    **Доступные статусы:**
                    - **ACTIVE** - Активный сотрудник
                    - **DELETED** - Удаленный сотрудник
                    - **NEW** - Новый сотрудник
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список сотрудников",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-by-date-status")
    public ResponseEntity<List<EmployeeDtoResponse>> getByStatusActive(
            @Parameter(
                    description = "Статус активности",
                    example = "ACTIVE",
                    required = true
            )
            @RequestParam Active status) {
        return ResponseEntity.ok(employeeService.getByStatusActive(status).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск по отделу",
            description = """
                    Возвращает сотрудников, работающих в указанном отделе.
                    
                    **Поиск выполняется:**
                    - По названию отдела на русском (departmentRu)
                    - По названию отдела на кыргызском (departmentKg)
                    
                    **Пример:** "Отдел маркетинга" или "Маркетинг бөлүмү"
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список сотрудников",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-by-date-department")
    public ResponseEntity<List<EmployeeDtoResponse>> getByDepartment(
            @Parameter(
                    description = "Название отдела (RU или KG)",
                    example = "Отдел маркетинга",
                    required = true
            )
            @RequestParam String department) {
        return ResponseEntity.ok(employeeService.getByDepartment(department).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск по дате трудоустройства",
            description = """
                    Возвращает сотрудников, устроенных в указанную дату.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    **Пример:** 2020-01-15
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список сотрудников",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-by-date-employment")
    public ResponseEntity<List<EmployeeDtoResponse>> getByDateEmployment(
            @Parameter(
                    description = "Дата трудоустройства",
                    example = "2020-01-15",
                    required = true
            )
            @RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(employeeService.getByDateEmployment(dateEmployment).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск по дате увольнения",
            description = """
                    Возвращает сотрудников, уволенных в указанную дату.
                    
                    **Формат даты:** ISO 8601 (YYYY-MM-DD)
                    **Пример:** 2024-06-01
                    **Примечание:** null означает, что сотрудник еще работает
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список сотрудников",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-by-date-dismissal")
    public ResponseEntity<List<EmployeeDtoResponse>> getByDateDismissal(
            @Parameter(
                    description = "Дата увольнения",
                    example = "2024-06-01",
                    required = true
            )
            @RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(employeeService.getByDateDismissal(dateDismissal).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Получить сотрудника по ID",
            description = """
                    Возвращает полную информацию о сотруднике по его ID.
                    
                    **Включает:**
                    - Персональные данные (ФИО, дата рождения)
                    - Отдел (RU/KG)
                    - Даты трудоустройства/увольнения
                    - Фотография
                    - Статус активности
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о сотруднике",
                            content = @Content(schema = @Schema(implementation = EmployeeDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
            }
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<EmployeeDtoResponse> getById(
            @Parameter(
                    description = "ID сотрудника",
                    example = "1",
                    required = true
            )
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.getById(id)));
    }

    @Operation(
            summary = "Получить всех сотрудников",
            description = """
                    Возвращает список всех активных сотрудников.
                    
                    **Исключает:** Сотрудников со статусом DELETED
                    **Сортировка:** По ФИО (alphabetical)
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех сотрудников",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeDtoResponse.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован")
            }
    )
    @GetMapping("/get-all")
    public ResponseEntity<List<EmployeeDtoResponse>> getAll() {
        return ResponseEntity.ok(employeeService.getAll().stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Создать нового сотрудника",
            description = """
                    Добавляет нового сотрудника в систему.
                    
                    **Обязательные поля:**
                    - fullName - ФИО сотрудника
                    - dateBerth - Дата рождения
                    - departmentRu/departmentKg - Отдел
                    - dateEmployment - Дата трудоустройства
                    
                    **Опциональные поля:**
                    - dateDismissal - Дата увольнения (null если работает)
                    - image - Фотография
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сотрудник успешно создан",
                            content = @Content(schema = @Schema(implementation = EmployeeDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<EmployeeDtoResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные нового сотрудника",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EmployeeDtoRequest.class))
            )
            @RequestBody EmployeeDtoRequest dtoRequest) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.save(employeeMapper.mapDtoToEntity(dtoRequest))));
    }

    @Operation(
            summary = "Обновить данные сотрудника",
            description = """
                    Обновляет данные сотрудника.
                    
                    **Примечание:**
                    - ID обязателен для определения сотрудника
                    - Передавайте только изменяемые поля
                    - null поля не обновляются
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные сотрудника обновлены",
                            content = @Content(schema = @Schema(implementation = EmployeeDtoResponse.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
            }
    )
    @PutMapping("/update")
    public ResponseEntity<EmployeeDtoResponse> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для обновления сотрудника",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EmployeeDtoRequestUpdate.class))
            )
            @RequestBody EmployeeDtoRequestUpdate dtoRequest) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.update(employeeMapper.mapDtoToEntityUpdate(dtoRequest))));
    }

    @Operation(
            summary = "Удалить сотрудника по ID",
            description = """
                    Удаляет сотрудника по идентификатору (soft delete).
                    
                    **Примечание:**
                    - Сотрудник не удаляется физически
                    - Устанавливается статус DELETED
                    - dateDismissal заполняется текущей датой
                    
                    **Требуемая роль:** ADMIN
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудник успешно удален"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
                    @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
            }
    )
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(
            @Parameter(
                    description = "ID сотрудника для удаления",
                    example = "1",
                    required = true
            )
            @PathVariable("id") Long id) {
        employeeService.deleteById(id);
    }
}
