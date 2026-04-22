package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.TeacherDtoRequest;
import com.example.site_pl_99.dto.TeacherDtoRequestUpdate;
import com.example.site_pl_99.dto.TeacherDtoResponse;
import com.example.site_pl_99.entity.TeacherEntity;

public class
TeacherMapper {
    public static TeacherEntity toEntity(TeacherDtoRequest teacherDtoRequest) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setFullName(teacherDtoRequest.getFullName());
        teacherEntity.setDateBerth(teacherDtoRequest.getDateBirth());
        teacherEntity.setLinkPortfolio(teacherDtoRequest.getLinkPortfolio());
        teacherEntity.setDateEmployment(teacherDtoRequest.getDateEmployment());
        if (teacherDtoRequest.getImage() != null) {
            teacherEntity.setImage(ImageMapper.mapDtoToEntity(teacherDtoRequest.getImage()));
        }
        return teacherEntity;
    }

    public static TeacherEntity toEntityUpdate(TeacherDtoRequestUpdate teacherDtoRequest) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(teacherDtoRequest.getId());
        teacherEntity.setFullName(teacherDtoRequest.getFullName());
        teacherEntity.setDateBerth(teacherDtoRequest.getDateBirth());
        teacherEntity.setLinkPortfolio(teacherDtoRequest.getLinkPortfolio());
        teacherEntity.setDateEmployment(teacherDtoRequest.getDateEmployment());
        if (teacherDtoRequest.getImage() != null) {
            teacherEntity.setImage(ImageMapper.mapDtoToEntity(teacherDtoRequest.getImage()));
        }
        return teacherEntity;
    }

    public static TeacherDtoResponse dtoResponse(TeacherEntity teacherEntity) {
        TeacherDtoResponse teacherDtoResponse = new TeacherDtoResponse();
        teacherDtoResponse.setId(teacherEntity.getId());
        teacherDtoResponse.setFullName(teacherEntity.getFullName());
        teacherDtoResponse.setDateBirth(teacherEntity.getDateBerth());
        teacherDtoResponse.setLinkPortfolio(teacherEntity.getLinkPortfolio());
        teacherDtoResponse.setActive(teacherEntity.getActive());
        teacherDtoResponse.setDateEmployment(teacherEntity.getDateEmployment());
        teacherDtoResponse.setDateDismissal(teacherEntity.getDateDismissal());
        if (teacherEntity.getImage() != null) {
            teacherDtoResponse.setImage(ImageMapper.mapEntityToDto(teacherEntity.getImage()));
        }
        return teacherDtoResponse;
    }
}
