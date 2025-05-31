package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.QualificationDtoRequest;
import com.example.site_pl_99.entity.QualificationEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.WorkerEntity;
import com.example.site_pl_99.mapper.QualificationMapper;
import com.example.site_pl_99.repository.QualificationRepository;
import com.example.site_pl_99.service.QualificationService;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepository;
    private final WorkerService workerService;

    public QualificationServiceImpl(QualificationRepository qualificationRepository, WorkerService workerService) {
        this.qualificationRepository = qualificationRepository;
        this.workerService = workerService;
    }

    @Override
    public QualificationEntity save(QualificationDtoRequest qualificationDtoRequest, UserEntity user, Long workerId) {
        return qualificationRepository
                .save(QualificationMapper.toQualificationEntity(qualificationDtoRequest, user)
                        .setWorkerEntities(
                                List.of(
                                        workerService.getWorkerId(workerId)
                                )
                        )
                );
    }

    @Override
    public QualificationEntity getQualificationId(Long id) {
        return qualificationRepository.findById(id).orElseThrow(() -> new RuntimeException("такого напровления нет"));
    }

    @Override
    public List<QualificationEntity> getAllQualification() {
        return qualificationRepository.findAll();
    }

    @Override
    public QualificationEntity setWorkerIntoQualification(List<Long> listId, Long qualificationId) {
        List<WorkerEntity> workerEntities = new ArrayList<>();
        for(Long id : listId){
            workerEntities.add(workerService.getWorkerId(id));
        }
        QualificationEntity qualificationEntity = qualificationRepository.findById(qualificationId).orElseThrow(() -> new RuntimeException("нет такого напроваления"));
        return qualificationRepository.save(qualificationEntity.setWorkerEntities(workerEntities));
    }

    @Override
    public List<QualificationEntity> getQualificationByWorker(Long id) {
        return qualificationRepository.findQualificationEntitiesByWorkerEntities(workerService.getWorkerId(id)).orElseThrow(() -> new RuntimeException("нет такого работника"));
    }
}
