package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.dto.CourseDtoResponse;
import com.example.site_pl_99.entity.UserEntity;

import java.util.List;

public interface CourseService {
    CourseDtoRequest saveCourse(CourseDtoRequest courseDtoRequest, Long workerId, UserEntity user);
    CourseDtoResponse getCourseId(Long id);
    List<CourseDtoResponse> getAllCourse();
    CourseDtoResponse getCourseByWorker(Long workerId);
    CourseDtoResponse getCourseByUser(Long userId);
}
