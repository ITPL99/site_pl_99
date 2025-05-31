package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.AvatarDtoRequest;
import com.example.site_pl_99.dto.AvatarDtoResponse;
import com.example.site_pl_99.entity.AvatarWorkerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AvatarMapper {

    public static AvatarWorkerEntity toAvatarWorkerEntity(AvatarDtoRequest avatarDtoRequest) {
        return new AvatarWorkerEntity().setFileName(avatarDtoRequest.getFileName());
    }

    public static AvatarDtoResponse toAvatarDtoResponse(AvatarWorkerEntity avatarWorkerEntity) {
        return new AvatarDtoResponse().setId(avatarWorkerEntity.getId())
                .setFileName(avatarWorkerEntity.getFileName())
                .setDateUpload(avatarWorkerEntity.getDateUpload())
                .setDateUpdated(avatarWorkerEntity.getDateUpdated())
                .setWorkerId(avatarWorkerEntity.getWorker().getId())
                .setUserId(avatarWorkerEntity.getUser().getId());
    }

    public static List<AvatarDtoResponse> toAvatarDtoResponseList(List<AvatarWorkerEntity> avatarWorkerEntities) {
        return avatarWorkerEntities.stream().map(AvatarMapper::toAvatarDtoResponse).collect(Collectors.toList());
    }
}
