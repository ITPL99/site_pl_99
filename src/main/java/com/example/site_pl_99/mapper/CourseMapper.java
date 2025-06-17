package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.dto.CourseDtoResponse;
import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.enums.CourseType;

public class CourseMapper {
    public static CourseEntity toEntity(CourseDtoRequest courseDtoRequest) {
        return new CourseEntity().setCourseType(CourseType.valueOf(courseDtoRequest.getCourseType()))
                .setTitleRu(courseDtoRequest.getTitle())
                .setTitleKg(courseDtoRequest.getTitle())
                .setDescriptionRu(courseDtoRequest.getDescription())
                .setDescriptionKg(courseDtoRequest.getDescription())
                .setPrice(courseDtoRequest.getPrice())
                .setDateStart(courseDtoRequest.getDateStart())
                .setDateEnd(courseDtoRequest.getDateEnd());
    }
    public static CourseDtoResponse toCourseResponse (CourseEntity courseEntity) {
        return new CourseDtoResponse().setId(courseEntity.getId())
                .setTitleRu(courseEntity.getTitleRu())
                .setTitleKg(courseEntity.getTitleKg())
                .setDescriptionRu(courseEntity.getDescriptionRu())
                .setDescriptionKg(courseEntity.getDescriptionKg())
                .setPrice(courseEntity.getPrice())
                .setDateStarted(courseEntity.getDateStart())
                .setDateEnd(courseEntity.getDateEnd())
                .setImageCourseId(courseEntity.getId());
    }
}
