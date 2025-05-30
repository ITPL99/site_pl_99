package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.WorkerDtoResponse;

import java.util.List;

public interface WorkerService {
    WorkerDtoResponse saveWorker(Long userId);
    WorkerDtoResponse getWorkerId(Long id);
    List<WorkerDtoResponse> getAllWorkers();
    WorkerDtoResponse getWorkerByName(String name);
    List<WorkerDtoResponse> getWorkerByProfession(String profession);
}
