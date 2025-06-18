package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.CourseDtoResponse;
import com.example.site_pl_99.entity.CourseEntity;

public class CourseMapper {
    public static CourseDtoResponse mapEntityToDtoResponse(CourseEntity byTitle) {
        return new  CourseDtoResponse();
    }
}
