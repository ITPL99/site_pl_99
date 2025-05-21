package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.Course;

import java.util.List;

public interface CourseService {
    Course save(Course course);
    List<Course> getAll();
    Course getById(Long id);
}
