package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.TeacherDtoRequest;
import com.example.site_pl_99.dto.TeacherDtoResponse;
import com.example.site_pl_99.entity.TeacherEntity;

public class TeacherMapper {
    public static TeacherEntity toEntity(TeacherDtoRequest teacherDtoRequest) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setFullName(teacherDtoRequest.getFullName());
        teacherEntity.setDateBerth(teacherDtoRequest.getDateBirth());
        teacherEntity.setLinkPortfolio(teacherDtoRequest.getLinkPortfolio());
        return teacherEntity;
    }
    public static TeacherDtoResponse dtoResponse(TeacherEntity teacherEntity) {
        TeacherDtoResponse teacherDtoResponse = new TeacherDtoResponse();
        teacherDtoResponse.setFullName(teacherEntity.getFullName());
        teacherDtoResponse.setDateBirth(teacherEntity.getDateBerth());
        teacherDtoResponse.setLinkPortfolio(teacherEntity.getLinkPortfolio());
        return teacherDtoResponse;
    }
}
