package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.entity.UserEntity;

import java.util.List;

public interface CourseService {
    CourseEntity saveCourse(CourseDtoRequest courseDtoRequest, Long workerId, UserEntity user);
    CourseEntity getCourseId(Long id);
    List<CourseEntity> getAllCourse();
    List <CourseEntity> getCourseByWorker(Long workerId);
    List <CourseEntity> getCourseByUser(Long userId);
}
