package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.QualificationDtoResponse;
import com.example.site_pl_99.entity.QualificationEntity;
import com.example.site_pl_99.service.QualificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {
    @Override
    public QualificationDtoResponse saveQualification(QualificationEntity qualificationEntity, Long userId) {
        return null;
    }

    @Override
    public QualificationDtoResponse getQualificationId(Long id) {
        return null;
    }

    @Override
    public List<QualificationDtoResponse> getAllQualification() {
        return List.of();
    }

    @Override
    public QualificationDtoResponse getQualificationByUser(Long userId) {
        return null;
    }
}
