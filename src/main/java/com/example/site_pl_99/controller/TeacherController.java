package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.TeacherDtoRequest;
import com.example.site_pl_99.dto.TeacherDtoRequestUpdate;
import com.example.site_pl_99.dto.TeacherDtoResponse;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.mapper.TeacherMapper;
import com.example.site_pl_99.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@SecurityRequirement(name = "bearerAuth")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "Получить учителя по ФИО")
    @GetMapping("/get-by-full-name")
    public ResponseEntity<?> findByFullName(
            @Parameter(description = "Полное имя учителя") @RequestParam String fullName) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.getFullName(fullName)));
    }

    @Operation(summary = "Получить учителей по дате рождения")
    @GetMapping("/get-all-by-date-berth")
    public ResponseEntity<List<TeacherDtoResponse>> findByDateBirth(
            @Parameter(description = "Дата рождения") @RequestParam LocalDate DateBirth) {
        return ResponseEntity.ok(teacherService.getByDateBerth(DateBirth).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(summary = "Поиск учителей по имени (частичное совпадение)")
    @GetMapping("/get-search-by-name")
    public ResponseEntity<List<TeacherDtoResponse>> getByName(
            @Parameter(description = "Имя учителя") @RequestParam String name) {
        return ResponseEntity.ok(teacherService.searchByName(name).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(summary = "Получить учителей по статусу активности")
    @GetMapping("/get-by-acive-status")
    public ResponseEntity<List<TeacherDtoResponse>> getByActive(
            @Parameter(description = "Статус активности") @RequestParam Active active) {
        return ResponseEntity.ok(teacherService.getByStatusActive(active).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(summary = "Получить учителей по кафедре (портфолио)")
    @GetMapping("/get-all-by-portfolio")
    public ResponseEntity<List<TeacherDtoResponse>> findByPortfolio(
            @Parameter(description = "Название кафедры") @RequestParam String department) {
        return ResponseEntity.ok(teacherService.getByPortfolio(department).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(summary = "Получить учителей по дате трудоустройства")
    @GetMapping("/get-all-by-date-employment")
    public ResponseEntity<List<TeacherDtoResponse>> findByDateEmployment(
            @Parameter(description = "Дата трудоустройства") @RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(teacherService.getByDateEmployment(dateEmployment).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(summary = "Получить учителей по дате увольнения")
    @GetMapping("/get-all-by-date-dismissal")
    public ResponseEntity<List<TeacherDtoResponse>> findByDateDismissal(
            @Parameter(description = "Дата увольнения") @RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(teacherService.getDateDismissal(dateDismissal).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(summary = "Получить всех учителей")
    @GetMapping("/get-all")
    public ResponseEntity<List<TeacherDtoResponse>> findAll() {
        return ResponseEntity.ok(teacherService.getAll().stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @Operation(summary = "Получить учителя по ID")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<TeacherDtoResponse> findById(
            @Parameter(description = "ID учителя") @PathVariable Long id) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.getById(id)));
    }

    @Operation(summary = "Обновить данные учителя по ID")
    @PutMapping("/update-by-id")
    public ResponseEntity<TeacherDtoResponse> updateById(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для обновления учителя",
                    required = true
            )
            @RequestBody TeacherDtoRequestUpdate teacherDtoRequest) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(
                teacherService.update(TeacherMapper.toEntityUpdate(teacherDtoRequest))
        ));
    }

    @Operation(summary = "Сохранить нового учителя")
    @PostMapping("/save")
    public ResponseEntity<TeacherDtoResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные нового учителя",
                    required = true
            )
            @RequestBody TeacherDtoRequest teacherEntity) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(
                teacherService.save(TeacherMapper.toEntity(teacherEntity))
        ));
    }

    @Operation(summary = "Удалить учителя по ID")
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(
            @Parameter(description = "ID учителя") @PathVariable Long id) {
        teacherService.deleteById(id);
    }
}
