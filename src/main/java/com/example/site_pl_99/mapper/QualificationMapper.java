package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.QualificationDtoRequest;
import com.example.site_pl_99.dto.QualificationDtoResponse;
import com.example.site_pl_99.entity.BaseEntity;
import com.example.site_pl_99.entity.QualificationEntity;
import com.example.site_pl_99.entity.UserEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QualificationMapper {

    public static QualificationEntity toQualificationEntity(QualificationDtoRequest qualificationDtoRequest, UserEntity userEntity) {
        return new QualificationEntity().setTitle(qualificationDtoRequest.getTitle())
                .setDescription(qualificationDtoRequest.getDescription())
                .setUser(userEntity);
    }

    public static QualificationDtoResponse toQualificationDtoResponse(QualificationEntity qualificationEntity) {
        QualificationDtoResponse qualificationDtoResponse = new QualificationDtoResponse()
                .setId(qualificationEntity.getId())
                .setTitle(qualificationEntity.getTitle())
                .setDescription(qualificationEntity.getDescription())
                .setUserId(qualificationEntity.getUser().getId())
                .setDateCreated(qualificationEntity.getDateCreated())
                .setDateUpdated(qualificationEntity.getDateUpdated());
                if(Objects.nonNull(qualificationEntity.getWorkerEntities())) qualificationDtoResponse.setWorkersIdList(qualificationEntity.getWorkerEntities().stream().map(BaseEntity::getId).toList());
                return qualificationDtoResponse;
    }

    public static List<QualificationDtoResponse> toQualificationDtoResponseList(List<QualificationEntity> qualificationEntityList) {
        return qualificationEntityList.stream().map(QualificationMapper::toQualificationDtoResponse).collect(Collectors.toList());
    }
}
