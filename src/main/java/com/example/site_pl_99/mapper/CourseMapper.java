package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.dto.CourseDtoResponseKg;
import com.example.site_pl_99.dto.CourseDtoResponseRu;
import com.example.site_pl_99.entity.CourseEntity;

public class CourseMapper {
    public static CourseEntity toEntity(CourseDtoRequest request){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setType(request.getCourseType());
        courseEntity.setTitleRu(request.getTitleRu());
        courseEntity.setTitleKg(request.getTitleKg());
        courseEntity.setDescriptionRu(request.getDescriptionRu());
        courseEntity.setDescriptionKg(request.getDescriptionKg());
        courseEntity.setPrice(request.getPrice());
        return courseEntity;
    }


    public static CourseDtoResponseRu mapEntityToDtoResponse(CourseEntity byTitle) {
        CourseDtoResponseRu courseDtoResponse = new CourseDtoResponseRu();
        courseDtoResponse.setCourseType(byTitle.getType());
        courseDtoResponse.setTitleRu(byTitle.getTitleRu());
        courseDtoResponse.setDescriptionRu(byTitle.getDescriptionRu());
        courseDtoResponse.setPrice(byTitle.getPrice());
        courseDtoResponse.setDateStart(byTitle.getDateStart());
        courseDtoResponse.setDateEnd(byTitle.getDateEnd());
        return courseDtoResponse;
    }
    public static CourseDtoResponseKg CourseDtoResponseKg(CourseEntity entity){
        CourseDtoResponseKg courseDtoResponseKg = new CourseDtoResponseKg();
        courseDtoResponseKg.setCourseType(entity.getType());
        courseDtoResponseKg.setTitleKg(entity.getTitleKg());
        courseDtoResponseKg.setDescriptionKg(entity.getDescriptionKg());
        courseDtoResponseKg.setPrice(entity.getPrice());
        courseDtoResponseKg.setDateStart(entity.getDateStart());
        courseDtoResponseKg.setDateEnd(entity.getDateEnd());
        return courseDtoResponseKg;
    }
}
