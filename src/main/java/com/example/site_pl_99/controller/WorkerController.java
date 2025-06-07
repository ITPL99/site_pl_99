package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.WorkerDtoRequest;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.WorkerMapper;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.service.WorkerService;
import com.example.site_pl_99.utils.Internalization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
@Tag(name = "Контроллер работника")
@RestController
@RequestMapping("/api/workers")
@SecurityRequirement(name = "bearerAuth")
public class WorkerController {
    private final WorkerService workerService;
    private final AuthService authService;

    public WorkerController(WorkerService workerService, AuthService authService) {
        this.workerService = workerService;
        this.authService = authService;
    }
    @Operation(summary = "добавить работника",
    description = "Создаёт нового работника на основе переданных данных.")
    @ApiResponses(value = {@ApiResponse(responseCode = "400",description = "некоректный ввод"),
            @ApiResponse(responseCode = "200", description = "Успешно выполнено"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "409",description = "Ресурс уже существует и не может быть дубликатов")})
    @PostMapping("/add-worker")
    public ResponseEntity<?> addNewWorker(@Parameter(description = "Данные для создания нового работника") @RequestBody WorkerDtoRequest workerDtoRequest) throws BaseException {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponse(workerService.saveWorker(workerDtoRequest, authService.getCurrentUser())));
    }
    @Operation(summary = "Вернуть работника по указанному ID",
    description = "Возвращает работника по его ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно выполнено"),
            @ApiResponse(responseCode = "400",description = "некоректный ввод"),
            @ApiResponse(responseCode = "404", description = "Не найден")})
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getWorker(@Parameter(description = "Id работника")@PathVariable Long id) throws BaseException {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponse(workerService.getWorkerId(id)));
    }
    @Operation(summary = "Вернуть всех работников",
    description = "Возвращает список всех зарегестрированных работников")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно выполнено")})
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllWorker() throws BaseException {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponseList(workerService.getAllWorkers()));
    }
    @Operation(
            summary = "Получить работника по имени",
            description = "Возвращает данные работника по полному имени"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "404", description = "Не найден"),
    @ApiResponse(responseCode = "400",description = "некоректный ввод"),
            @ApiResponse(responseCode = "200", description = "Успешно выполнено")})

    @GetMapping("/get-by-name")
    public ResponseEntity<?> getWorker(@RequestParam String name) throws BaseException {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponse(workerService.getWorkerByName(name)));
    }
    @Operation(
            summary = "Получить работников по профессии",
            description = "Возвращает список работников, у которых указана заданная профессия. Требуется авторизация."
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно выполнено"),
            @ApiResponse(responseCode = "400",description = "некоректный ввод"),
            @ApiResponse(responseCode = "404", description = "Не найден")})
    @GetMapping("/get-by-profession")
    public ResponseEntity<?> getByProfession(@Parameter(description = "Название профессий")@RequestParam String profession) {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponseList(workerService.getWorkerByProfession(profession)));
    }
}
