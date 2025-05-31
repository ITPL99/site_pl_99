package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.WorkerDtoRequest;
import com.example.site_pl_99.entity.QualificationEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.WorkerEntity;
import com.example.site_pl_99.mapper.WorkerMapper;
import com.example.site_pl_99.repository.QualificationRepository;
import com.example.site_pl_99.repository.WorkerRepository;
import com.example.site_pl_99.service.QualificationService;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final QualificationRepository qualificationRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository, QualificationRepository qualificationRepository) {
        this.workerRepository = workerRepository;
        this.qualificationRepository = qualificationRepository;
    }

    @Override
    public WorkerEntity saveWorker(WorkerDtoRequest worker, UserEntity user) {
        WorkerEntity workerEntity = WorkerMapper.toWorkerEntity(worker, user);
        workerEntity
                .setQualificationEntities(qualificationRepository.findQualificationEntitiesByWorkerEntities(workerEntity).orElseThrow(() -> new RuntimeException("ошибка при сохронении")));
        return workerRepository.save(workerEntity);
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
