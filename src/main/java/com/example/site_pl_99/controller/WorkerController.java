package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.WorkerDTO;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.Worker;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/create-worker")
    public ResponseEntity<String> createWorker(@RequestBody Worker worker){
        workerService.save(worker);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-worker-id/{id}")
    public WorkerDTO getWorkerByid(@PathVariable Long id){
        return new WorkerDTO(workerService.getWorkerById(id));
    }

    @GetMapping("/get-all-workers")
    public List<WorkerDTO> getAll(){
        List<WorkerDTO> workerDTOS = new ArrayList<>();
        for(Worker worker : workerService.getAll()){
            WorkerDTO workerDTO = new WorkerDTO(worker);
            workerDTOS.add(workerDTO);
        }
        return workerDTOS;
    }
}
