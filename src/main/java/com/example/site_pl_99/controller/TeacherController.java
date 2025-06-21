package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.TeacherDtoRequest;
import com.example.site_pl_99.entity.TeacherEntity;
import com.example.site_pl_99.mapper.TeacherMapper;
import com.example.site_pl_99.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    public final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/get-by-full-name")
    public ResponseEntity<?> findByFullName(@RequestParam String fullName) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.getFullName(fullName)));
    }

    @GetMapping("/get-all-by-master-content-name")
    public ResponseEntity<?> findByMasterContentName(@RequestParam String masterContentName) {
        return ResponseEntity.ok(teacherService.getAllMastersContentName(masterContentName).stream().map(TeacherMapper::dtoResponse).toList());
    }

    @GetMapping("/get-all-by-master-date-berth")
    public ResponseEntity<?> findByMasterDateBericth(@RequestParam LocalDate masterDateBericth) {
        return ResponseEntity.ok(teacherService.getAllMastersByDateBerth(masterDateBericth).stream().map(TeacherMapper::dtoResponse).toList());
    }

    @GetMapping("/get-all-by-portfolio")
    public ResponseEntity<?> findByPortfolio(@RequestParam String department) {
        return ResponseEntity.ok(teacherService.getAllMastersByPortfolio(department).stream().map(TeacherMapper::dtoResponse).toList());
    }

    @GetMapping("/get-all-by-date-employment")
    public ResponseEntity<?> findByDateEmployment(@RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(teacherService.getAllMastersByDateEmployment(dateEmployment).stream().map(TeacherMapper::dtoResponse).toList());
    }

    @GetMapping("/get-all-by-date-dismissal")
    public ResponseEntity<?> findByDateDismissal(@RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(teacherService.getAllMastersByDateDismissal(dateDismissal).stream().map(TeacherMapper::dtoResponse).toList());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.getById(id)));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TeacherDtoRequest teacherEntity) {
        return ResponseEntity.ok(TeacherMapper.dtoResponse(teacherService.save(TeacherMapper.toEntity(teacherEntity))));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
    }

}
