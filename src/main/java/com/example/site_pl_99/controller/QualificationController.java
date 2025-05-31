package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.QualificationDtoRequest;
import com.example.site_pl_99.entity.QualificationEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.mapper.QualificationMapper;
import com.example.site_pl_99.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qualification")
public class QualificationController {
    private final QualificationService qualificationService;

    @Autowired
    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @PostMapping("/add-qualification/{workerId}")
    public ResponseEntity<?> addQualification(@PathVariable Long workerId, @RequestBody QualificationDtoRequest qualification) {
        try {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponse(qualificationService.save(qualification,(UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),workerId)));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При сохранении направления произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/get-qualification/{id}")
    public ResponseEntity<?> getQualification(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponse(qualificationService.getQualificationId(id)));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При получении направления произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/get-qualification-all")
    public ResponseEntity<?> getAllQualification() {
        try {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponseList(qualificationService.getAllQualification()));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При получении направления произошла ошибка: " + e.getMessage());
        }
    }

    @PostMapping("/set-worker-into-qualification/{qualificationId}")
    public ResponseEntity<?> setQualification(@PathVariable Long qualificationId, @RequestBody List<Long> workerIds) {
        try {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponse(qualificationService.setWorkerIntoQualification(workerIds,qualificationId)));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При сохранении рабочих в напровления произошла ошибка: " + e.getMessage());
        }
    }
}
