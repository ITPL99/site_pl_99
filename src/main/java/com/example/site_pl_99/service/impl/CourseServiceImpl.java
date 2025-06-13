package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.WorkerEntity;
import com.example.site_pl_99.excaption.CourseIsNotFoundException;
import com.example.site_pl_99.mapper.CourseMapper;
import com.example.site_pl_99.repository.CourseRepository;
import com.example.site_pl_99.service.CourseService;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final WorkerService workerService;
    private final UserService userService;
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, WorkerService workerService, UserService userService) {
        this.courseRepository = courseRepository;
        this.workerService = workerService;
        this.userService = userService;
    }

    @Override
    public CourseEntity saveCourse(CourseDtoRequest courseDtoRequest, Long workerId, UserEntity user) {
        return courseRepository.save(CourseMapper.toCourseEntity(courseDtoRequest,user, workerService.getWorkerId(workerId)));
    }

    @Override
    public CourseEntity getCourseId(Long id) {
        return courseRepository.findById(id).orElseThrow(()-> new CourseIsNotFoundException("error.findCourse"));
    }

    @Override
    public List<CourseEntity> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public List<CourseEntity> getCourseByWorker(Long workerId) {
        return courseRepository.findCourseEntitiesByWorker(workerService.getWorkerId(workerId)).orElseThrow(() -> new CourseIsNotFoundException("error.findCourse"));
    }

    @Override
    public List<CourseEntity> getCourseByUser(Long userId) {
        return courseRepository.findCourseEntitiesByUser(userService.getById(userId)).orElseThrow(() -> new CourseIsNotFoundException("error.findCourse"));
    }
}
