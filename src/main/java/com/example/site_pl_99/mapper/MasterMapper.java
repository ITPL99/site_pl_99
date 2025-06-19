package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.MasterDtoRequest;
import com.example.site_pl_99.dto.MasterDtoResponse;
import com.example.site_pl_99.dto.MasterDtoResponseKg;
import com.example.site_pl_99.entity.MasterEntity;

public class MasterMapper {
    public static MasterEntity toEntity(MasterDtoRequest request) {
        MasterEntity masterEntity = new MasterEntity();
        masterEntity.setFullName(request.getFullName());
        masterEntity.setDateBerth(request.getBirthDate());
        masterEntity.setProfessionRu(request.getProfessionRu());
        masterEntity.setProfessionKg(request.getProfessionKg());
        return masterEntity;
    }
    public static MasterDtoResponse toDto(MasterEntity masterEntity) {
        MasterDtoResponse masterDtoResponse = new MasterDtoResponse();
        masterDtoResponse.setFullName(masterEntity.getFullName());
        masterDtoResponse.setBirthDate(masterEntity.getDateBerth());
        masterDtoResponse.setProfessionRu(masterEntity.getProfessionRu());
        masterDtoResponse.setActive(masterEntity.getActive());
        masterDtoResponse.setDateDismissal(masterEntity.getDateDismissal());
        masterDtoResponse.setDateEmployment(masterEntity.getDateEmployment());
        return masterDtoResponse;
    }
    public static MasterDtoResponseKg toDtoKg(MasterEntity masterEntity) {
        MasterDtoResponseKg masterDtoResponseKg = new MasterDtoResponseKg();
        masterDtoResponseKg.setFullName(masterEntity.getFullName());
        masterDtoResponseKg.setBirthDate(masterEntity.getDateBerth());
        masterDtoResponseKg.setProfessionKg(masterEntity.getProfessionKg());
        masterDtoResponseKg.setActive(masterEntity.getActive());
        masterDtoResponseKg.setDateDismissal(masterEntity.getDateDismissal());
        masterDtoResponseKg.setDateEmployment(masterEntity.getDateEmployment());
        return masterDtoResponseKg;
    }
}
