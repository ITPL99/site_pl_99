package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.CourseEntity;
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
    public CourseEntity getByTitle(String title) {
        return courseRepository.findByTitleRuOrTitleKg(title, title).orElseThrow(() ->  new NotFoundException("Курсы с таким названием не найдены"));
    }

    @Override
    public List<CourseEntity> getAllCourseByType(CourseType type) {
        return courseRepository.findAllByType(type).orElseThrow(() ->  new NotFoundException("Курсы данного типу не найдены"));
    }

    @Override
    public List<CourseEntity> getAllCourseByPrice(Double price) {
        return courseRepository.findAllByPrice(price).orElseThrow(() ->  new NotFoundException("Курсы с анной ценой не найдены"));
    }

    @Override
    public List<CourseEntity> getAllCourseByDateStart(LocalDate dateStart) {
        return courseRepository.findAllByDateStart(dateStart).orElseThrow(() ->  new NotFoundException("По данной дате курсы не найденны"));
    }

    @Override
    public List<CourseEntity> getAllCourseByDateEnd(LocalDate dateEnd) {
        return courseRepository.findAllByDateEnd(dateEnd).orElseThrow(() ->  new NotFoundException("По данной дате курсы не найденны"));

    }

    @Override
    public CourseEntity getById(Long id) {
        return courseRepository.findById(id).orElseThrow(() ->  new NotFoundException("Курсы с ID: " + id +" не найдены"));
    }

    @Override
    public CourseEntity save(CourseEntity entity) {
        return courseRepository.save(entity);
    }

    @Override
    public List<CourseEntity> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
