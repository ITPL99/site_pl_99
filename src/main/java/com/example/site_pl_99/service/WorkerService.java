package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.WorkerEntity;

import java.util.List;

public interface WorkerService {
    WorkerEntity saveWorker(UserEntity user);
    WorkerEntity getWorkerId(Long id);
    List<WorkerEntity> getAllWorkers();
    WorkerEntity getWorkerByName(String name);
    List<WorkerEntity> getWorkerByProfession(String profession);
}
