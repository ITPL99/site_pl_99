package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.WorkerDTO;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.Worker;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    private final WorkerService workerService;
    private final UserService userService;

    @Autowired
    public WorkerController(WorkerService workerService, UserService userService) {
        this.workerService = workerService;
        this.userService = userService;
    }

    @PostMapping("create-worker/{id}")
    public ResponseEntity<String> createWorker(@RequestBody Worker worker, @PathVariable Long id){
        UserEntity user = userService.getById(id);
        if (!user.getRoleEntityList().get(0).equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Только Админ может создовать");
        }
        workerService.save(worker);
        return ResponseEntity.ok().build();
    }

    @GetMapping("get-worker-id/{id}")
    public WorkerDTO getWorkerByid(@PathVariable Long id){
        Worker worker = workerService.getWorkerById(id);
        WorkerDTO workerDTO = new WorkerDTO()
                .setAge(worker.getAge())
                .setBio(worker.getBio())
                .setName(worker.getName())
                .setId(worker.getId());
        return workerDTO;
    }

    @GetMapping("get-all-workers")
    public List<WorkerDTO> getAll(){
        List<Worker> workers = workerService.getAll();
        List<WorkerDTO> workerDTOS = new ArrayList<>();
        for(Worker worker : workers){
            WorkerDTO workerDTO = new WorkerDTO()
                    .setAge(worker.getAge())
                    .setBio(worker.getBio())
                    .setName(worker.getName())
                    .setId(worker.getId());
            workerDTOS.add(workerDTO);
        }
        return workerDTOS;
    }
}
