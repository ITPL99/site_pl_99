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

@RestController
@RequestMapping("/api/master")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Master Controller", description = "Управление мастерами (поиск, фильтрация, создание, обновление, удаление)")
public class MasterController {

    private final MasterServiceImpl masterService;

    public MasterController(MasterServiceImpl masterService) {
        this.masterService = masterService;
    }

    @Operation(
            summary = "Получить мастера по ФИО",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Мастер найден")
            }
    )
    @GetMapping("/get-by-full-name")
    public ResponseEntity<MasterDtoResponse> getMasterByFullName(
            @Parameter(description = "Полное имя мастера", example = "Ivan Ivanov") @RequestParam String fullName) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.getFullName(fullName)));
    }

    @Operation(
            summary = "Поиск мастеров по имени (частичное совпадение)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список мастеров найден")
            }
    )
    @GetMapping("/get-search-by-name")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByName(
            @Parameter(description = "Имя или часть имени мастера", example = "Ivan") @RequestParam String name) {
        return ResponseEntity.ok(masterService.searchByName(name).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастеров по статусу активности",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список мастеров по статусу")
            }
    )
    @GetMapping("/get-by-active-status")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByActiveStatus(
            @Parameter(description = "Статус активности", example = "ACTIVE") @RequestParam Active status) {
        return ResponseEntity.ok(masterService.getAllActiveStatus(status).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастеров по дате рождения",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список мастеров по дате рождения")
            }
    )
    @GetMapping("/get-all-by-date-berth")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByDateBerth(
            @Parameter(description = "Дата рождения", example = "1990-01-01") @RequestParam LocalDate dateBerth) {
        return ResponseEntity.ok(masterService.getByDateBerth(dateBerth).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастеров по профессии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список мастеров по профессии")
            }
    )
    @GetMapping("/get-all-by-profession")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByProfession(
            @Parameter(description = "Профессия", example = "Welder") @RequestParam String profession) {
        return ResponseEntity.ok(masterService.getByProfession(profession).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастеров по дате приема на работу",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список мастеров по дате приема")
            }
    )
    @GetMapping("/get-all-by-date-employment")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByDateEmployment(
            @Parameter(description = "Дата приема", example = "2020-05-10") @RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(masterService.getByDateEmployment(dateEmployment).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастеров по дате увольнения",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список мастеров по дате увольнения"
                   )
            }
    )
    @GetMapping("/get-all-by-date-dismissal")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByDateDismissal(
            @Parameter(description = "Дата увольнения", example = "2023-09-15") @RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(masterService.getByDateDismissal(dateDismissal).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @Operation(
            summary = "Получить мастера по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Мастер найден")
            }
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MasterDtoResponse> getMasterById(
            @Parameter(description = "ID мастера", example = "1") @PathVariable("id") Long id) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.getById(id)));
    }

    @Operation(
            summary = "Получить всех мастеров",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список всех мастеров")
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
            responses = {
                    @ApiResponse(responseCode = "200", description = "Мастер успешно создан")
            }
    )
    @PostMapping("/save")
    public ResponseEntity<MasterDtoResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "DTO мастера для создания",
                    required = true
            )
            @RequestBody MasterDtoRequest entity) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.save(MasterMapper.toEntity(entity))));
    }

    @Operation(
            summary = "Обновить данные мастера",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Мастер успешно обновлён"
                    ),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные")
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
            responses = {
                    @ApiResponse(responseCode = "200", description = "Мастер удалён")
            }
    )
    @DeleteMapping("/delete-by-id/{id}")
    public void delete(
            @Parameter(description = "ID мастера", example = "5") @PathVariable("id") Long id) {
        masterService.deleteById(id);
    }
}
