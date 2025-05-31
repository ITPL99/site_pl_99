package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.WorkerEntity;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Override
    public WorkerEntity saveWorker(UserEntity user) {
        return null;
    }

    @Override
    public WorkerEntity getWorkerId(Long id) {
        return null;
    }

    @Override
    public List<WorkerEntity> getAllWorkers() {
        return List.of();
    }

    @Override
    public WorkerEntity getWorkerByName(String name) {
        return null;
    }

    @Override
    public List<WorkerEntity> getWorkerByProfession(String profession) {
        return List.of();
    }
}
