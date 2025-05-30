package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.QualificationDtoResponse;
import com.example.site_pl_99.entity.QualificationEntity;
import com.example.site_pl_99.entity.UserEntity;

import java.util.List;

public interface QualificationService {
    QualificationDtoResponse saveQualification(QualificationEntity qualificationEntity, Long userId);
    QualificationDtoResponse getQualificationId(Long id);
    List<QualificationDtoResponse> getAllQualification();
    QualificationDtoResponse getQualificationByUser(Long userId);
}
