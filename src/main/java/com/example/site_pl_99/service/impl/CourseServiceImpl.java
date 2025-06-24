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
        return courseRepository.findAllCoursesByTitleRuOrTitleKg(title, title)
                .orElseThrow(() -> new NotFoundException("Курсы с таким названием не найдены"));
    }

    @Override
    public List<CourseEntity> getAllCourseByType(CourseType type) {
        return courseRepository.findAllByType(type)
                .orElseThrow(() -> new RuntimeException("Курсы с типом " + type + " не найдены"));
    }

    @Override
    public List<CourseEntity> getAllCourseByPrice(Double price) {
        return courseRepository.findAllByPrice(price)
                .orElseThrow(() -> new RuntimeException("Курсы по цене " + price + " не найдены"));
    }

    @Override
    public List<CourseEntity> getAllCourseByDateStart(LocalDate dateStart) {
        return courseRepository.findAllByDateStart(dateStart)
                .orElseThrow(() -> new RuntimeException("Курсы с началом " + dateStart + " не найдены"));
    }

    @Override
    public List<CourseEntity> getAllCourseByDateEnd(LocalDate dateEnd) {
        return courseRepository.findAllByDateEnd(dateEnd)
                .orElseThrow(() -> new RuntimeException("Курсы с окончанием " + dateEnd + " не найдены"));
    }

    @Override
    public CourseEntity getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Курс с ID " + id + " не найден"));
    }

    @Override
    public CourseEntity save(CourseEntity entity) {
        if (entity.getTitleRu() == null || entity.getPrice() == null) {
            throw new RuntimeException("Название и цена курса обязательны");
        }

        return courseRepository.save(entity);
    }

    @Override
    public List<CourseEntity> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<CourseEntity> getAllActive() {
        return courseRepository.findAllByActive(Active.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Активные курсы не найдены"));
    }

    @Override
    public List<CourseEntity> getAllStatus(Active status) {
        return courseRepository.findAllByActive(status)
                .orElseThrow(() -> new NotFoundException("Курсы со статусом " + status + " не найдены"));
    }

    @Override
    public void deleteById(Long id) {
        CourseEntity courseEntity = getById(id);
        courseEntity.setActive(Active.DELETED);
        courseRepository.save(courseEntity);
    }
}