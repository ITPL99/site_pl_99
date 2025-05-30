package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.dto.CourseDtoResponse;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Override
    public CourseDtoRequest saveCourse(CourseDtoRequest courseDtoRequest, Long workerId, UserEntity user) {
        return null;
    }

    @Override
    public CourseDtoResponse getCourseId(Long id) {
        return null;
    }

    @Override
    public List<CourseDtoResponse> getAllCourse() {
        return List.of();
    }

    @Override
    public CourseDtoResponse getCourseByWorker(Long workerId) {
        return null;
    }

    @Override
    public CourseDtoResponse getCourseByUser(Long userId) {
        return null;
    }
}
