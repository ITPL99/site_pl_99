package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public CourseEntity saveCourse(CourseDtoRequest courseDtoRequest, Long workerId, UserEntity user) {
        return null;
    }

    @Override
    public CourseEntity getCourseId(Long id) {
        return null;
    }

    @Override
    public List<CourseEntity> getAllCourse() {
        return List.of();
    }

    @Override
    public CourseEntity getCourseByWorker(Long workerId) {
        return null;
    }

    @Override
    public CourseEntity getCourseByUser(Long userId) {
        return null;
    }
}
