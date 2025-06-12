package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.QualificationDtoRequest;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.QualificationMapper;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.service.QualificationService;
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

import java.util.List;
import java.util.Locale;
@Tag(name = "Контроллер квалификаций")
@RestController
@RequestMapping("/api/qualifications")
@SecurityRequirement(name = "bearerAuth")
public class QualificationController {
    private final QualificationService qualificationService;
    private final AuthService authService;
    private final WorkerService workerService;

    public QualificationController(QualificationService qualificationService, AuthService authService, WorkerService workerService) {
        this.qualificationService = qualificationService;
        this.authService = authService;
        this.workerService = workerService;
    }
    @Operation(
            summary = "Добавить новую квалификацию",
            description = "Создает новую квалификацию на основе переданных данных"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно создан новая квалификация"),
    @ApiResponse(responseCode = "409", description = "квалификация уже существует"),
    @ApiResponse(responseCode = "400",description = "некоректный ввод"),
    @ApiResponse(responseCode = "403", description = "Недостаточно прав")})

    @PostMapping("/add-qualification")
    public ResponseEntity<?> addNewQualification(@Parameter(description = "Данные новой квалификаций") @RequestBody QualificationDtoRequest qualificationDtoRequest) throws BaseException {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponse(qualificationService.save(qualificationDtoRequest, authService.getCurrentUser())));
    }
    @Operation(
            summary = "Добавить работников в квалификацию",
            description = "Добавляет одного или нескольких работников в указанную квалификацию по её ID."
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно добавлен"),
    @ApiResponse(responseCode = "400",description = "Некоректный ввод"),
     @ApiResponse(responseCode = "403", description = "Недостаточно прав для выполнения операции"),
    @ApiResponse(responseCode = "404", description = "работник или квалификация не найдена")
    })
    @PostMapping("/add-worker-into")
    public ResponseEntity<?> addWorkerIntoQualification(@Parameter(description = "ID работника")@RequestParam List<Long> workerId,@Parameter(description = "id квалификаций") @RequestParam Long qualificationId) throws BaseException {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponse(qualificationService.setWorkerIntoQualification(workerId, qualificationId)));
    }
    @Operation(summary = "вернуть все квалификаций",description = "возвращает список всех зарегестрированных квалификаций ")
    @ApiResponse(responseCode = "200", description = "Список квалификаций успешно возвращен")
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllQualifications() throws BaseException {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponseList(qualificationService.getAllQualification()));
    }
    @Operation(summary = "Вернуть квалицификацию по id работника",description = "Возвращает список квалификаций по указанному id работника")
    @GetMapping("/get-by-worker")
    public ResponseEntity<?> getQualificationByWorker(@Parameter(description = "id работника")@RequestParam Long workerId) throws BaseException {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponseList(qualificationService.getQualificationByWorker(workerId)));
    }
}
