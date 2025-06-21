package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.MasterDtoRequest;
import com.example.site_pl_99.dto.MasterDtoResponse;
import com.example.site_pl_99.entity.MasterEntity;
import org.springframework.context.i18n.LocaleContextHolder;

public class MasterMapper {
    public static MasterEntity toEntity(MasterDtoRequest request) {
        MasterEntity masterEntity = new MasterEntity();
        masterEntity.setFullName(request.getFullName());
        masterEntity.setDateBerth(request.getBirthDate());
        masterEntity.setProfessionRu(request.getProfessionRu());
        masterEntity.setProfessionKg(request.getProfessionKg());
        masterEntity.setImage(ImageMapper.mapDtoToEntity(request.getImage()));
        return masterEntity;
    }
    public static MasterDtoResponse toDto(MasterEntity masterEntity) {
        MasterDtoResponse masterDtoResponse = new MasterDtoResponse();
        masterDtoResponse.setFullName(masterEntity.getFullName());
        masterDtoResponse.setBirthDate(masterEntity.getDateBerth());
        if(LocaleContextHolder.getLocale().getLanguage().equalsIgnoreCase("ru")){
            masterDtoResponse.setProfession(masterEntity.getProfessionRu());
        }
        if(LocaleContextHolder.getLocale().getLanguage().equalsIgnoreCase("kg")){
            masterDtoResponse.setProfession(masterEntity.getProfessionKg());
        }
        masterDtoResponse.setActive(masterEntity.getActive());
        masterDtoResponse.setDateDismissal(masterEntity.getDateDismissal());
        masterDtoResponse.setDateEmployment(masterEntity.getDateEmployment());
        return masterDtoResponse;
    }
}
