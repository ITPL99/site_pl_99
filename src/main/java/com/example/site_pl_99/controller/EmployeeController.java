package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.EmployeeDtoRequest;
import com.example.site_pl_99.dto.EmployeeDtoRequestUpdate;
import com.example.site_pl_99.dto.EmployeeDtoResponse;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.mapper.EmployeeMapper;
import com.example.site_pl_99.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Employee Controller", description = "Управление сотрудниками: поиск, создание, обновление и удаление")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @Operation(
            summary = "Получить сотрудника по ФИО",
            description = "Возвращает сотрудника по полному имени",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены")
            }
    )
    @GetMapping("/get-by-full-name")
    public ResponseEntity<EmployeeDtoResponse> getFullName(
            @Parameter(description = "Полное имя сотрудника", required = true) @RequestParam String fullName) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.getFullName(fullName)));
    }

    @Operation(
            summary = "Поиск сотрудников по части имени",
            description = "Возвращает список сотрудников, содержащих подстроку в имени",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены")
            }
    )
    @GetMapping("/search-by-Name")
    public ResponseEntity<List<EmployeeDtoResponse>> getContentName(
            @Parameter(description = "Часть имени сотрудника", required = true) @RequestParam String fullName) {
        return ResponseEntity.ok(employeeService.searchByName(fullName).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск по дате рождения",
            description = "Возвращает сотрудников по указанной дате рождения",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены")
            }
    )
    @GetMapping("/get-by-date-date-berth")
    public ResponseEntity<List<EmployeeDtoResponse>> getByDateBerth(
            @Parameter(description = "Дата рождения (формат YYYY-MM-DD)", required = true) @RequestParam LocalDate dateBerth) {
        return ResponseEntity.ok(employeeService.getByDateBerth(dateBerth).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск по статусу активности",
            description = "Возвращает список сотрудников по статусу активности",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены")
            }
    )
    @GetMapping("/get-by-date-status")
    public ResponseEntity<List<EmployeeDtoResponse>> getByStatusActive(
            @Parameter(description = "Статус активности", required = true) @RequestParam Active status) {
        return ResponseEntity.ok(employeeService.getByStatusActive(status).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск по отделу",
            description = "Возвращает сотрудников, работающих в указанном отделе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены")
            }
    )
    @GetMapping("/get-by-date-department")
    public ResponseEntity<List<EmployeeDtoResponse>> getByDepartment(
            @Parameter(description = "Название отдела", required = true) @RequestParam String department) {
        return ResponseEntity.ok(employeeService.getByDepartment(department).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск по дате трудоустройства",
            description = "Возвращает сотрудников, устроенных в указанную дату",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены")
            }
    )
    @GetMapping("/get-by-date-employment")
    public ResponseEntity<List<EmployeeDtoResponse>> getByDateEmployment(
            @Parameter(description = "Дата трудоустройства (формат YYYY-MM-DD)", required = true) @RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(employeeService.getByDateEmployment(dateEmployment).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Поиск по дате увольнения",
            description = "Возвращает сотрудников, уволенных в указанную дату",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены")
            }
    )
    @GetMapping("/get-by-date-dismissal")
    public ResponseEntity<List<EmployeeDtoResponse>> getByDateDismissal(
            @Parameter(description = "Дата увольнения (формат YYYY-MM-DD)", required = true) @RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(employeeService.getByDateDismissal(dateDismissal).stream()
                .map(employeeMapper::mapEntityToDtoResponse)
                .toList());
    }

    @Operation(
            summary = "Получить сотрудника по ID",
            description = "Возвращает сотрудника по его идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены")
            }
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<EmployeeDtoResponse> getById(
            @Parameter(description = "ID сотрудника", required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.getById(id)));
    }

    @Operation(
            summary = "Получить всех сотрудников",
            description = "Возвращает список всех сотрудников",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получены")
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
            description = "Добавляет нового сотрудника в систему",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Создан")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<EmployeeDtoResponse> save(
            @Parameter(description = "Данные нового сотрудника", required = true)
            @RequestBody EmployeeDtoRequest dtoRequest) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.save(employeeMapper.mapDtoToEntity(dtoRequest))));
    }

    @Operation(
            summary = "Обновить сотрудника",
            description = "Обновляет данные сотрудника по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Обновлен")
            }
    )
    @PutMapping("/update")
    public ResponseEntity<EmployeeDtoResponse> update(
            @Parameter(description = "Данные для обновления сотрудника", required = true)
            @RequestBody EmployeeDtoRequestUpdate dtoRequest) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.update(employeeMapper.mapDtoToEntityUpdate(dtoRequest))));
    }

    @Operation(
            summary = "Удалить сотрудника по ID",
            description = "Удаляет сотрудника по идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Удолен по ID")
            }
    )
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(
            @Parameter(description = "ID сотрудника", required = true) @PathVariable("id") Long id) {
        employeeService.deleteById(id);
    }
}
