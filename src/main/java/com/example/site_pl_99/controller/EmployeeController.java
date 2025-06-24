package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.EmployeeDtoRequest;
import com.example.site_pl_99.dto.EmployeeDtoResponse;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.mapper.EmployeeMapper;
import com.example.site_pl_99.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Сотрудники", description = "Операции для управления сотрудниками")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @Operation(summary = "Получить сотрудника по ФИО")
    @PostMapping("/get-by-date-name")
    public ResponseEntity<EmployeeDtoResponse> getFullName(
            @RequestParam @Parameter(description = "Полное имя сотрудника") String fullName) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.getFullName(fullName)));
    }

    @Operation(summary = "Получить список сотрудников, содержащих имя")
    @PostMapping("/get-by-date-containing-name")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesContentName(
            @RequestParam @Parameter(description = "Часть ФИО сотрудника") String fullName) {
        return ResponseEntity.ok(employeeService.getAllEmployeesContentName(fullName)
                .stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @Operation(summary = "Получить список сотрудников по дате рождения")
    @PostMapping("/get-by-date-date-berth")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesByDateBerth(
            @RequestParam @Parameter(description = "Дата рождения (формат: yyyy-MM-dd)") LocalDate dateBerth) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDateBerth(dateBerth)
                .stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @Operation(summary = "Получить список сотрудников по статусу активности")
    @PostMapping("/get-by-date-status")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesByStatusActive(
            @RequestParam @Parameter(description = "Статус активности сотрудника") Active status) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByStatusActive(status)
                .stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @Operation(summary = "Получить список сотрудников по отделу")
    @PostMapping("/get-by-date-department")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesByDepartment(
            @RequestParam @Parameter(description = "Название отдела") String department) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDepartment(department)
                .stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @Operation(summary = "Получить список сотрудников по дате трудоустройства")
    @PostMapping("/get-by-date-employment")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesByDateEmployment(
            @RequestParam @Parameter(description = "Дата трудоустройства (формат: yyyy-MM-dd)") LocalDate dateEmployment) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDateEmployment(dateEmployment)
                .stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @Operation(summary = "Получить список сотрудников по дате увольнения")
    @PostMapping("/get-by-date-dismissal")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesByDateDismissal(
            @RequestParam @Parameter(description = "Дата увольнения (формат: yyyy-MM-dd)") LocalDate dateDismissal) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDateDismissal(dateDismissal)
                .stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @Operation(summary = "Получить всех сотрудников (расширенные данные)")
    @GetMapping("/get-all")
    public ResponseEntity<List<EmployeeDtoResponse>> getFullAll() {
        return ResponseEntity.ok(employeeService.getFullAll()
                .stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @Operation(
            summary = "Получить сотрудника по ID",
            description = "Возвращает сотрудника по его уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудник найден",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeDtoResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
            }
    )
    @PostMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(
            @PathVariable("id") @Parameter(description = "ID сотрудника") Long id) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.getById(id)));
    }

    @Operation(summary = "Создать нового сотрудника")
    @ApiResponse(responseCode = "200", description = "Сотрудник успешно создан")
    @PostMapping("/create")
    public ResponseEntity<?> save(
            @RequestBody @Parameter(description = "DTO запроса для создания сотрудника", required = true)
            EmployeeDtoRequest dtoRequest) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(
                employeeService.save(employeeMapper.mapDtoToEntity(dtoRequest))));
    }

    @Operation(summary = "Получить всех сотрудников (базовая информация)")
    @GetMapping("/all")
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(employeeService.getAll()
                .stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @Operation(summary = "Удалить сотрудника по ID")
    @ApiResponse(responseCode = "200", description = "Сотрудник успешно удалён")
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(
            @PathVariable("id") @Parameter(description = "ID сотрудника") Long id) {
        employeeService.deleteById(id);
    }
}
