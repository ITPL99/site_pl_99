package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.QualificationDtoRequest;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.QualificationMapper;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.service.QualificationService;
import com.example.site_pl_99.service.WorkerService;
import com.example.site_pl_99.utils.Internalization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/qualifications")
public class QualificationController {
    private final QualificationService qualificationService;
    private final AuthService authService;
    private final WorkerService workerService;

    public QualificationController(QualificationService qualificationService, AuthService authService, WorkerService workerService) {
        this.qualificationService = qualificationService;
        this.authService = authService;
        this.workerService = workerService;
    }

    @PostMapping("/add-qualification")
    public ResponseEntity<?> addNewQualification(@RequestBody QualificationDtoRequest qualificationDtoRequest) throws BaseException {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponse(qualificationService.save(qualificationDtoRequest, authService.getCurrentUser())));
    }

    @PostMapping("/add-worker-into")
    public ResponseEntity<?> addWorkerIntoQualification(@RequestParam List<Long> workerId, @RequestParam Long qualificationId) throws BaseException {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponse(qualificationService.setWorkerIntoQualification(workerId, qualificationId)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllQualifications() throws BaseException {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponseList(qualificationService.getAllQualification()));
    }

    @GetMapping("/get-by-worker")
    public ResponseEntity<?> getQualificationByWorker(@RequestParam Long workerId) throws BaseException {
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponseList(qualificationService.getQualificationByWorker(workerId)));
    }
}
