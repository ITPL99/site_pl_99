package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.WorkerDtoRequest;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.mapper.WorkerMapper;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping("/add-worker")
    public ResponseEntity<?> addWorker(@RequestBody WorkerDtoRequest workerDtoRequest) {
        try {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponse(workerService.saveWorker(workerDtoRequest,  (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal())));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При сохронении работника произошла ошибка: "+e.getMessage());
        }
    }

    @GetMapping("/get-worker-id/{workerId}")
    public ResponseEntity<?> getWorkerById(@PathVariable Long workerId) {
        try {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponse(workerService.getWorkerId(workerId)));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При получении работника произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/get-worker-all")
    public ResponseEntity<?> getWorkerAll() {
        try {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponseList(workerService.getAllWorkers()));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При получении работника произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/get-worker-full-name/{fullName}")
    public ResponseEntity<?> getWorkerFullName(@PathVariable String fullName) {
        try {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponse(workerService.getWorkerByName(fullName)));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При получении работника произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/get-worker-profession/{profession}")
    public ResponseEntity<?> getWorkerProfession(@PathVariable String profession) {
        try {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponseList(workerService.getWorkerByProfession(profession)));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При получении работника произошла ошибка: " + e.getMessage());
        }
    }
}
