package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.MainMenuDtoRequest;
import com.example.site_pl_99.dto.MainMenuDtoResponse;
import com.example.site_pl_99.entity.MainMenuEntity;
import org.springframework.context.i18n.LocaleContextHolder;

public class MainMenuMapper {
    public static MainMenuEntity toMainMenuEntity(MainMenuDtoRequest mainMenuDtoRequest) {
        MainMenuEntity mainMenuEntity = new MainMenuEntity();
        mainMenuEntity.setTitleRu(mainMenuDtoRequest.getTitleRu())
                .setTitleKg(mainMenuDtoRequest.getTitleKg())
                .setSubTitleRu(mainMenuDtoRequest.getSubTitleRu())
                .setSubTitleKg(mainMenuDtoRequest.getSubTitleKg())
                .setAmountStudents(mainMenuDtoRequest.getAmountStudents())
                .setAmountGraduates(mainMenuDtoRequest.getAmountGraduates())
                .setAmountPartners(mainMenuDtoRequest.getAmountPartners());
        if(mainMenuDtoRequest.getImage() != null) mainMenuEntity.setImage(ImageMapper.mapDtoToEntity(mainMenuDtoRequest.getImage()));
        return mainMenuEntity;
    }

    public static MainMenuDtoResponse toMainMenuDtoResponse(MainMenuEntity mainMenuEntity) {
        MainMenuDtoResponse mainMenuDtoResponse = new MainMenuDtoResponse();
        mainMenuDtoResponse.setId(mainMenuEntity.getId());
        if(LocaleContextHolder.getLocaleContext().getLocale().getLanguage().equals("ru")){
            mainMenuDtoResponse.setTitle(mainMenuEntity.getTitleRu())
                    .setSubTitle(mainMenuEntity.getSubTitleRu());
        }else{
            mainMenuDtoResponse.setTitle(mainMenuEntity.getTitleKg())
                    .setSubTitle(mainMenuEntity.getSubTitleKg());
        }
        mainMenuDtoResponse.setAmountStudents(mainMenuEntity.getAmountStudents())
                .setAmountGraduates(mainMenuEntity.getAmountGraduates())
                .setAmountPartners(mainMenuEntity.getAmountPartners());
        if(mainMenuEntity.getImage() != null) mainMenuDtoResponse.setImage(ImageMapper.mapEntityToDto(mainMenuEntity.getImage()));
        return mainMenuDtoResponse;
    }
}
