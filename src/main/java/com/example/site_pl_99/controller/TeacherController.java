package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.MasterDtoResponse;
import com.example.site_pl_99.dto.TeacherDtoRequest;
import com.example.site_pl_99.dto.TeacherDtoRequestUpdate;
import com.example.site_pl_99.dto.TeacherDtoResponse;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.mapper.TeacherMapper;
import com.example.site_pl_99.service.TeacherService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@SecurityRequirement(name = "bearerAuth")
public class TeacherController {
    public final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/get-by-full-name")
    public ResponseEntity<?> findByFullName(@RequestParam String fullName) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.getFullName(fullName)));
    }

    @GetMapping("/get-all-by-date-berth")
    public ResponseEntity<List<TeacherDtoResponse>> findByDateBirth(@RequestParam LocalDate DateBirth) {
        return ResponseEntity.ok(teacherService.getByDateBerth(DateBirth).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @GetMapping("/get-search-by-name")
    public ResponseEntity<List<TeacherDtoResponse>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(teacherService.searchByName(name).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @GetMapping("/get-by-acive-status")
    public ResponseEntity<List<TeacherDtoResponse>> getByActive(@RequestParam Active active) {
        return ResponseEntity.ok(teacherService.getByStatusActive(active).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @GetMapping("/get-all-by-portfolio")
    public ResponseEntity<List<TeacherDtoResponse>> findByPortfolio(@RequestParam String department) {
        return ResponseEntity.ok(teacherService.getByPortfolio(department).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @GetMapping("/get-all-by-date-employment")
    public ResponseEntity<List<TeacherDtoResponse>> findByDateEmployment(@RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(teacherService.getByDateEmployment(dateEmployment).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @GetMapping("/get-all-by-date-dismissal")
    public ResponseEntity<List<TeacherDtoResponse>> findByDateDismissal(@RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(teacherService.getDateDismissal(dateDismissal).stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TeacherDtoResponse>> findAll() {
        return ResponseEntity.ok(teacherService.getAll().stream()
                .map(TeacherMapper::dtoResponse)
                .toList());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<TeacherDtoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.getById(id)));
    }

    @PutMapping("/update-by-id")
    public ResponseEntity<TeacherDtoResponse> updateById(@RequestBody TeacherDtoRequestUpdate teacherDtoRequest) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.update(TeacherMapper.toEntityUpdate(teacherDtoRequest))));
    }

    @PostMapping("/save")
    public ResponseEntity<TeacherDtoResponse> save(@RequestBody TeacherDtoRequest teacherEntity) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.save(TeacherMapper.toEntity(teacherEntity))));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
    }

}
