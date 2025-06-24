package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.TeacherDtoRequest;
import com.example.site_pl_99.mapper.TeacherMapper;
import com.example.site_pl_99.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/teacher")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Учителя", description = "Операции для управления данными учителей")
public class TeacherController {
    public final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "Найти учителя по ФИО")
    @GetMapping("/get-by-full-name")
    public ResponseEntity<?> findByFullName(
            @RequestParam @Parameter(description = "Полное имя учителя") String fullName) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.getFullName(fullName)));
    }

    @Operation(summary = "Найти всех учителей по содержимому мастер-названия")
    @GetMapping("/get-all-by-master-content-name")
    public ResponseEntity<?> findByMasterContentName(
            @RequestParam @Parameter(description = "Содержимое мастер-названия") String masterContentName) {
        return ResponseEntity.ok(
                teacherService.getAllMastersContentName(masterContentName)
                        .stream().map(TeacherMapper::dtoResponse).toList()
        );
    }

    @Operation(summary = "Найти всех учителей по дате рождения")
    @GetMapping("/get-all-by-master-date-berth")
    public ResponseEntity<?> findByMasterDateBericth(
            @RequestParam @Parameter(description = "Дата рождения") LocalDate masterDateBericth) {
        return ResponseEntity.ok(
                teacherService.getAllMastersByDateBerth(masterDateBericth)
                        .stream().map(TeacherMapper::dtoResponse).toList()
        );
    }

    @Operation(summary = "Найти всех учителей по отделению (портфолио)")
    @GetMapping("/get-all-by-portfolio")
    public ResponseEntity<?> findByPortfolio(
            @RequestParam @Parameter(description = "Название отделения (портфолио)") String department) {
        return ResponseEntity.ok(
                teacherService.getAllMastersByPortfolio(department)
                        .stream().map(TeacherMapper::dtoResponse).toList()
        );
    }

    @Operation(summary = "Найти всех учителей по дате трудоустройства")
    @GetMapping("/get-all-by-date-employment")
    public ResponseEntity<?> findByDateEmployment(
            @RequestParam @Parameter(description = "Дата трудоустройства") LocalDate dateEmployment) {
        return ResponseEntity.ok(
                teacherService.getAllMastersByDateEmployment(dateEmployment)
                        .stream().map(TeacherMapper::dtoResponse).toList()
        );
    }

    @Operation(summary = "Найти всех учителей по дате увольнения")
    @GetMapping("/get-all-by-date-dismissal")
    public ResponseEntity<?> findByDateDismissal(
            @RequestParam @Parameter(description = "Дата увольнения") LocalDate dateDismissal) {
        return ResponseEntity.ok(
                teacherService.getAllMastersByDateDismissal(dateDismissal)
                        .stream().map(TeacherMapper::dtoResponse).toList()
        );
    }

    @Operation(summary = "Найти учителя по ID")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> findById(
            @PathVariable @Parameter(description = "ID учителя") Long id) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.getById(id)));
    }

    @Operation(
            summary = "Сохранить или обновить учителя",
            requestBody = @RequestBody(
                    description = "Данные учителя",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TeacherDtoRequest.class))
            )
    )
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TeacherDtoRequest teacherEntity) {
        return ResponseEntity.ok(
                TeacherMapper.dtoResponse(teacherService.save(TeacherMapper.toEntity(teacherEntity)))
        );
    }

    @Operation(summary = "Удалить учителя по ID")
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(
            @PathVariable @Parameter(description = "ID учителя") Long id) {
        teacherService.deleteById(id);
    }
}
