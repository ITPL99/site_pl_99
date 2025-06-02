package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.WorkerDtoRequest;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.WorkerMapper;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.service.WorkerService;
import com.example.site_pl_99.utils.Internalization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {
    private final WorkerService workerService;
    private final AuthService authService;

    public WorkerController(WorkerService workerService, AuthService authService) {
        this.workerService = workerService;
        this.authService = authService;
    }

    @PostMapping("/add-worker")
    public ResponseEntity<?> addNewWorker(@RequestBody WorkerDtoRequest workerDtoRequest) throws BaseException {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponse(workerService.saveWorker(workerDtoRequest, authService.getCurrentUser())));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getWorker(@PathVariable Long id) throws BaseException {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponse(workerService.getWorkerId(id)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllWorker() throws BaseException {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponseList(workerService.getAllWorkers()));
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<?> getWorker(@RequestParam String name) throws BaseException {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponse(workerService.getWorkerByName(name)));
    }

    @GetMapping("/get-by-profession")
    public ResponseEntity<?> getByProfession(@RequestParam String profession) {
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponseList(workerService.getWorkerByProfession(profession)));
    }
}
