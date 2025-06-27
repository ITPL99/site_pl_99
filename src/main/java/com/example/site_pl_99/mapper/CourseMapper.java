package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.dto.CourseDtoResponse;
import com.example.site_pl_99.dto.CoursePreviewDto;
import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.enums.CourseType;
import com.example.site_pl_99.excaption.IncorectInputException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        courseDtoResponse.setId(byTitle.getId());
        courseDtoResponse.setActive(byTitle.getActive().name());
        courseDtoResponse.setCourseType(byTitle.getType().name());
        if(LocaleContextHolder.getLocale().getLanguage().equalsIgnoreCase("ru")) {
            courseDtoResponse.setTitle(byTitle.getTitleRu());
            courseDtoResponse.setDescription(byTitle.getDescriptionRu());
        }else{
            courseDtoResponse.setTitle(byTitle.getTitleKg());
            courseDtoResponse.setDescription(byTitle.getDescriptionKg());
        }
        courseDtoResponse.setPrice(byTitle.getPrice());
        courseDtoResponse.setImageFileName(byTitle.getImage().getFileName());
        courseDtoResponse.setDateStart(byTitle.getDateStart());
        courseDtoResponse.setDateEnd(byTitle.getDateEnd());
        return courseDtoResponse;
    }

    public static CoursePreviewDto mapToPreviewEntity(CourseEntity courseEntity) {
        CoursePreviewDto coursePreviewDto = new CoursePreviewDto();
        coursePreviewDto.setId(courseEntity.getId());
        coursePreviewDto.setCourseType(courseEntity.getType().name());
        if(LocaleContextHolder.getLocaleContext().getLocale().getLanguage().equalsIgnoreCase("ru")) {
            coursePreviewDto.setTitle(courseEntity.getTitleRu());
        }else {
            coursePreviewDto.setTitle(courseEntity.getTitleKg());
        }
        coursePreviewDto.setPrice(courseEntity.getPrice());
        coursePreviewDto.setDateCreated(courseEntity.getDateStart());
        coursePreviewDto.setDateEnd(courseEntity.getDateEnd());
        coursePreviewDto.setActive(courseEntity.getActive().name());
        if(courseEntity.getImage() != null) coursePreviewDto.setImage(ImageMapper.mapEntityToDto(courseEntity.getImage()));
        return coursePreviewDto;
    }

    public static List<CoursePreviewDto> mapListEntityToPreviewDtoList(List<CourseEntity> courseEntityList) {
        return courseEntityList.stream().map(CourseMapper::mapToPreviewEntity).collect(Collectors.toList());
    }
}
