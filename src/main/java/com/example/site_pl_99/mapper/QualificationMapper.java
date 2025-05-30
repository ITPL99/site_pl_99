package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.QualificationDtoRequest;
import com.example.site_pl_99.dto.QualificationDtoResponse;
import com.example.site_pl_99.entity.BaseEntity;
import com.example.site_pl_99.entity.QualificationEntity;
import com.example.site_pl_99.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class QualificationMapper {

    public static QualificationEntity toQualificationEntity(QualificationDtoRequest qualificationDtoRequest, UserEntity userEntity) {
        return new QualificationEntity().setTitle(qualificationDtoRequest.getTitle())
                .setDescription(qualificationDtoRequest.getDescription())
                .setUser(userEntity);
    }

    public static QualificationDtoResponse toQualificationDtoResponse(QualificationEntity qualificationEntity) {
        return new QualificationDtoResponse()
                .setId(qualificationEntity.getId())
                .setTitle(qualificationEntity.getTitle())
                .setDescription(qualificationEntity.getDescription())
                .setUserId(qualificationEntity.getUser().getId())
                .setDateCreated(qualificationEntity.getDateCreated())
                .setDateUpdated(qualificationEntity.getDateUpdated())
                .setWorkersIdList(qualificationEntity.getWorkerEntities().stream().map(BaseEntity::getId).toList());
    }

    public static List<QualificationDtoResponse> toQualificationDtoResponseList(List<QualificationEntity> qualificationEntityList) {
        return qualificationEntityList.stream().map(QualificationMapper::toQualificationDtoResponse).collect(Collectors.toList());
    }
}
