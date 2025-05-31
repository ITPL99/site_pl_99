package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.QualificationEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.service.QualificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {
    @Override
    public QualificationEntity save(QualificationEntity qualificationEntity, UserEntity user) {
        return null;
    }

    @Override
    public QualificationEntity getQualificationId(Long id) {
        return null;
    }

    @Override
    public List<QualificationEntity> getAllQualification() {
        return List.of();
    }

    @Override
    public QualificationEntity getQualificationByUser(Long userId) {
        return null;
    }
}
