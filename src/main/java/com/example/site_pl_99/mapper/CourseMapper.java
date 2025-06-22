package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.dto.CourseDtoResponse;
import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.enums.CourseType;
import com.example.site_pl_99.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public static CourseEntity toEntity(CourseDtoRequest request){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setType(CourseType.valueOf(request.getCourseType()));
        courseEntity.setTitleRu(request.getTitleRu());
        courseEntity.setTitleKg(request.getTitleKg());
        courseEntity.setDescriptionRu(request.getDescriptionRu());
        courseEntity.setDescriptionKg(request.getDescriptionKg());
        courseEntity.setImage(ImageMapper.mapDtoToEntity(request.getImage()));
        courseEntity.setPrice(request.getPrice());
        courseEntity.setDateStart(request.getDateStart());
        courseEntity.setDateEnd(request.getDateEnd());
        return courseEntity;
    }


    public static CourseDtoResponse mapEntityToDtoResponse(CourseEntity byTitle) {
        CourseDtoResponse courseDtoResponse = new CourseDtoResponse();
        courseDtoResponse.setCourseType(byTitle.getType().name());
        courseDtoResponse.setTitle(byTitle.getTitleRu());
        courseDtoResponse.setTitle(byTitle.getTitleKg());
        courseDtoResponse.setDescription(byTitle.getDescriptionRu());
        courseDtoResponse.setDescription(byTitle.getDescriptionKg());
        courseDtoResponse.setPrice(byTitle.getPrice());
        courseDtoResponse.setImageFileName(byTitle.getImage().getFileName());
        courseDtoResponse.setDateStart(byTitle.getDateStart());
        courseDtoResponse.setDateEnd(byTitle.getDateEnd());
        return courseDtoResponse;
    }
}
