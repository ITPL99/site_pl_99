package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.MasterDtoRequest;
import com.example.site_pl_99.dto.MasterDtoResponse;
import com.example.site_pl_99.mapper.MasterMapper;
import com.example.site_pl_99.service.impl.MasterServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
@Tag(name = "Мастера", description = "Операции для управления мастерами (поиск, создание, удаление)")
public class MasterController {

    private final MasterServiceImpl masterService;

    public MasterController(MasterServiceImpl masterService) {
        this.masterService = masterService;
    }

    @Operation(summary = "Получить мастера по ФИО")
    @GetMapping("/get-by-full-name")
    public ResponseEntity<?> getMasterByFullName(
            @RequestParam @Parameter(description = "Полное имя мастера") String fullName) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.getFullName(fullName)));
    }

    @Operation(summary = "Найти мастеров, содержащих подстроку в ФИО")
    @GetMapping("/get-all-by-content-name")
    public ResponseEntity<?> getMasterByContentName(
            @RequestParam @Parameter(description = "Подстрока для поиска в ФИО") String contentName) {
        return ResponseEntity.ok(masterService.getAllMastersContentName(contentName)
                .stream().map(MasterMapper::toDto).toList());
    }

    @Operation(summary = "Найти мастеров по дате рождения")
    @GetMapping("/get-all-by-date-berth")
    public ResponseEntity<?> getMasterByDateBerth(
            @RequestParam @Parameter(description = "Дата рождения (в формате yyyy-MM-dd)") LocalDate dateBerth) {
        return ResponseEntity.ok(masterService.getAllMastersByDateBerth(dateBerth)
                .stream().map(MasterMapper::toDto).toList());
    }

    @Operation(summary = "Найти мастеров по профессии")
    @GetMapping("/get-all-by-profession")
    public ResponseEntity<?> getMasterByProfession(
            @RequestParam @Parameter(description = "Название профессии") String profession) {
        return ResponseEntity.ok(masterService.getAllMastersByProfession(profession)
                .stream().map(MasterMapper::toDto).toList());
    }

    @Operation(summary = "Найти мастеров по дате приема на работу")
    @GetMapping("/get-all-by-date-employment")
    public ResponseEntity<?> getMasterByDateEmployment(
            @RequestParam @Parameter(description = "Дата приема на работу (yyyy-MM-dd)") LocalDate dateEmployment) {
        return ResponseEntity.ok(masterService.getAllMastersByDateEmployment(dateEmployment)
                .stream().map(MasterMapper::toDto).toList());
    }

    @Operation(summary = "Найти мастеров по дате увольнения")
    @GetMapping("/get-all-by-date-dismissal")
    public ResponseEntity<?> getMasterByDateDismissal(
            @RequestParam @Parameter(description = "Дата увольнения (yyyy-MM-dd)") LocalDate dateDismissal) {
        return ResponseEntity.ok(masterService.getAllMastersByDateDismissal(dateDismissal)
                .stream().map(MasterMapper::toDto).toList());
    }

    @Operation(summary = "Получить мастера по ID")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getMasterById(
            @PathVariable("id") @Parameter(description = "ID мастера") Long id) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.getById(id)));
    }

    @Operation(summary = "Получить список всех мастеров")
    @GetMapping("/get-all")
    public ResponseEntity<List<MasterDtoResponse>> getAll() {
        return ResponseEntity.ok(masterService.getAll()
                .stream().map(MasterMapper::toDto).toList());
    }

    @Operation(
            summary = "Создать нового мастера",
            requestBody = @RequestBody(
                    required = true,
                    description = "Объект мастера для сохранения",
                    content = @Content(schema = @Schema(implementation = MasterDtoRequest.class))
            )
    )
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MasterDtoRequest entity) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.save(MasterMapper.toEntity(entity))));
    }

    @Operation(summary = "Удалить мастера по ID")
    @DeleteMapping("/delete-by-id/{id}")
    public void delete(
            @PathVariable("id") @Parameter(description = "ID мастера") Long id) {
        masterService.deleteById(id);
    }
}
