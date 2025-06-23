package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.enums.CourseType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CourseService extends BaseService<CourseEntity> {
    List<CourseEntity> getByTitle(String title);
    List<CourseEntity> getAllCourseByType(CourseType type);
    List<CourseEntity> getAllCourseByPrice(Double price);
    List<CourseEntity> getAllCourseByDateStart(LocalDate dateStart);
    List<CourseEntity> getAllCourseByDateEnd(LocalDate dateEnd);

    List<CourseEntity> getAllActive();

    List<CourseEntity> getAllStatus(Active status);
}
