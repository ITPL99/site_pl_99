package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.TeacherDtoRequest;
import com.example.site_pl_99.dto.TeacherDtoResponse;
import com.example.site_pl_99.entity.TeacherEntity;

public class TeacherMapper {
    public static TeacherEntity toEntity(TeacherDtoRequest request) {
        return new TeacherEntity().setFullName(request.getFullName())
                .setBirthday(request.getBirthday())
                .setLinkPortfolio(request.getLinkPortfolio());
    }
    public static TeacherDtoResponse toResponse(TeacherEntity entity) {
        return new TeacherDtoResponse().setId(entity.getId())
                .setFullName(entity.getFullName())
                .setBirthday(entity.getBirthday())
                .setLinkPortfolio(entity.getLinkPortfolio())
                .setActive(entity.getActive())
                .setDateStarted(entity.getDateStarted())
                .setDateFired(entity.getDateFired())
                .setImageId(entity.getId());
    }
}
