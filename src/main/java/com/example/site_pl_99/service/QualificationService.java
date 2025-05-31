package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.QualificationDtoRequest;
import com.example.site_pl_99.entity.QualificationEntity;
import com.example.site_pl_99.entity.UserEntity;

import java.util.List;

public interface QualificationService {
    QualificationEntity save(QualificationDtoRequest qualificationDtoRequest, UserEntity user, Long workerId);
    QualificationEntity getQualificationId(Long id);
    List<QualificationEntity> getAllQualification();
    QualificationEntity setWorkerIntoQualification(List<Long> lisId, Long qualificationId);
    List<QualificationEntity> getQualificationByWorker(Long id);
}
