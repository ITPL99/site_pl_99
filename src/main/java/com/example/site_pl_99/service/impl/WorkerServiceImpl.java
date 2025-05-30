package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.WorkerDtoResponse;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Override
    public WorkerDtoResponse saveWorker(Long userId) {
        return null;
    }

    @Override
    public WorkerDtoResponse getWorkerId(Long id) {
        return null;
    }

    @Override
    public List<WorkerDtoResponse> getAllWorkers() {
        return List.of();
    }

    @Override
    public WorkerDtoResponse getWorkerByName(String name) {
        return null;
    }

    @Override
    public List<WorkerDtoResponse> getWorkerByProfession(String profession) {
        return List.of();
    }
}
