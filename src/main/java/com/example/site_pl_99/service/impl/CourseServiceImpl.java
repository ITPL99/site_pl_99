package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.enums.CourseType;
import com.example.site_pl_99.excaption.NotFoundException;
import com.example.site_pl_99.repository.CourseRepository;
import com.example.site_pl_99.service.CourseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseEntity> getByTitle(String title) {
        return courseRepository.findAllCoursesByTitleRuOrTitleKg(title, title).orElseThrow(()-> new NotFoundException("error.findCourse"));
    }

    @Override
    public List<CourseEntity> getAllCourseByType(CourseType type) {
        return courseRepository.findAllByType(type).orElseThrow(() ->  new NotFoundException("error.findCourse"));
    }

    @Override
    public List<CourseEntity> getAllCourseByPrice(Double price) {
        return courseRepository.findAllByPrice(price).orElseThrow(() ->  new NotFoundException("error.findCourse"));
    }

    @Override
    public List<CourseEntity> getAllCourseByDateStart(LocalDate dateStart) {
        return courseRepository.findAllByDateStart(dateStart).orElseThrow(() ->  new NotFoundException("error.findCourse"));
    }

    @Override
    public List<CourseEntity> getAllCourseByDateEnd(LocalDate dateEnd) {
        return courseRepository.findAllByDateEnd(dateEnd).orElseThrow(() ->  new NotFoundException("error.findCourse"));

    }

    @Override
    public CourseEntity getById(Long id) {
        return courseRepository.findById(id).orElseThrow(() ->  new NotFoundException("error.findCourse"));
    }

    @Override
    public CourseEntity save(CourseEntity entity) {
        if (entity.getId() == null) {
            entity.setActive(Active.NEW);
        }
        return courseRepository.save(entity);
    }

    @Override
    public List<CourseEntity> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<CourseEntity> getAllActive() {
        return courseRepository.findAllByActive(Active.ACTIVE).orElseThrow(()-> new NotFoundException("error.findCourse"));
    }

    @Override
    public List<CourseEntity> getAllStatus(Active status) {
        return courseRepository.findAllByActive(status).orElseThrow(()-> new NotFoundException("error.findCourse"));
    }

    @Override
    public void deleteById(Long id) {
        CourseEntity courseEntity = getById(id);
        courseEntity.setActive(Active.DELETED);
        courseRepository.save(courseEntity);
    }
}
