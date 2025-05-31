package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.WorkerDtoRequest;
import com.example.site_pl_99.dto.WorkerDtoResponse;
import com.example.site_pl_99.entity.AvatarWorkerEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.WorkerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WorkerMapper {

    public static WorkerEntity toWorkerEntity(WorkerDtoRequest workerDtoRequest, UserEntity userEntity) {

        return new WorkerEntity().setFullName(workerDtoRequest.getFullName())
                .setDateBirth(workerDtoRequest.getBirthDate())
                .setBiography(workerDtoRequest.getBiography())
                .setProfession(workerDtoRequest.getProfession())
                .setUser(userEntity);
    }

    public static WorkerDtoResponse toWorkerDtoResponse(WorkerEntity workerEntity) {
        WorkerDtoResponse workerDtoResponse = new WorkerDtoResponse().setId(workerEntity.getId())
                .setFullName(workerEntity.getFullName())
                .setBirthDate(workerEntity.getDateBirth())
                .setBiography(workerEntity.getBiography())
                .setProfession(workerEntity.getProfession())
                .setDateCreated(workerEntity.getDateCreated())
                .setDateUpdated(workerEntity.getDateUpdated())
                .setUserId(workerEntity.getUser().getId());
        if(Objects.nonNull(workerEntity.getAvatar())) workerDtoResponse .setAvatarId(workerEntity.getAvatar().getId());
        return workerDtoResponse;
    }

    public static List<WorkerDtoResponse> toWorkerDtoResponseList(List<WorkerEntity> workerEntityList) {
        return workerEntityList.stream().map(WorkerMapper::toWorkerDtoResponse).collect(Collectors.toList());
    }
}
