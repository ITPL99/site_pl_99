package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.MasterDtoRequest;
import com.example.site_pl_99.dto.MasterDtoResponse;
import com.example.site_pl_99.entity.MasterEntity;

public class MasterMapper {
    public static MasterEntity toEntity(MasterDtoRequest request) {
        return new MasterEntity().setFullName(request.getFullName())
                .setDateOfBirth(request.getBirthday())
                .setProfession(request.getProfession());
    }
    public static MasterDtoResponse toDtoResponse(MasterEntity entity) {
        return new MasterDtoResponse().setId(entity.getId())
                .setFullName(entity.getFullName())
                .setBirthday(entity.getDateOfBirth())
                .setProfession(entity.getProfession())
                .setDateStarted(entity.getDateStarted())
                .setDateFired(entity.getDateFired())
                .setId(entity.getId());
    }
}
