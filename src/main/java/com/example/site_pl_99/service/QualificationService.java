package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.QualificationEntity;
import com.example.site_pl_99.entity.UserEntity;

import java.util.List;

public interface QualificationService {
    QualificationEntity save(QualificationEntity qualificationEntity, UserEntity user);
    QualificationEntity getQualificationId(Long id);
    List<QualificationEntity> getAllQualification();
    QualificationEntity getQualificationByUser(Long userId);
}
