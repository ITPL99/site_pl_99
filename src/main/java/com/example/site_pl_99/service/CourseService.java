package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.CourseDtoRequestRegister;
import com.example.site_pl_99.entity.Course;

import java.util.List;

public interface CourseService {
    Course save(CourseDtoRequestRegister courseDtoRequestRegister);
    List<Course> getAll();
    Course getById(Long id);
}
