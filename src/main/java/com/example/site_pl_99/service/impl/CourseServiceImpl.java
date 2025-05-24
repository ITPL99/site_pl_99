package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.CourseDtoRequestRegister;
import com.example.site_pl_99.entity.Course;
import com.example.site_pl_99.repository.CourseRepository;
import com.example.site_pl_99.repository.WorkerRepository;
import com.example.site_pl_99.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final WorkerRepository workerRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, WorkerRepository workerRepository) {
        this.courseRepository = courseRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public Course save(CourseDtoRequestRegister courseDtoRequestRegister) {
        Course course = courseDtoRequestRegister.tyEntity();
        course.setWorker(workerRepository.findWorkerByName(courseDtoRequestRegister.getWorkerName()).orElseThrow(() -> new RuntimeException("Такой рабочий не найден")));
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Такой курс не найден"));
    }
}
