package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.WorkerDtoRequest;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.WorkerEntity;
import com.example.site_pl_99.mapper.WorkerMapper;
import com.example.site_pl_99.repository.WorkerRepository;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public WorkerEntity saveWorker(WorkerDtoRequest worker, UserEntity user) {
        return workerRepository.save(WorkerMapper.toWorkerEntity(worker, user));
    }

    @Override
    public WorkerEntity getWorkerId(Long id) {
        return workerRepository.findById(id).orElseThrow(() -> new RuntimeException("нет такого работника"));
    }

    @Override
    public List<WorkerEntity> getAllWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public WorkerEntity getWorkerByName(String name) {
        return workerRepository.findWorkerEntitiesByFullName(name).orElseThrow(() -> new RuntimeException("нет такого работника"));
    }

    @Override
    public List<WorkerEntity> getWorkerByProfession(String profession) {
        return workerRepository.findWorkerEntitiesByProfession(profession).orElseThrow(() -> new RuntimeException("нет такого работника"));
    }
}
