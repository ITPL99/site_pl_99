package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.enums.CourseType;
import com.example.site_pl_99.excaption.CourseNoFoundException;
import com.example.site_pl_99.excaption.UniquenessViolationException;
import com.example.site_pl_99.excaption.ValidationError;
import com.example.site_pl_99.repository.CourseRepository;
import com.example.site_pl_99.service.CourseService;
import org.springframework.dao.DataIntegrityViolationException;
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
        return courseRepository.findAllCoursesByTitleRuOrTitleKg(title, title);
    }

    @Override
    public List<CourseEntity> getAllCourseByType(CourseType type) {
        return courseRepository.findAllByType(type);
    }

    @Override
    public List<CourseEntity> getAllCourseByPrice(Double price) {
        return courseRepository.findAllByPrice(price);
    }

    @Override
    public List<CourseEntity> getAllCourseByDateStart(LocalDate dateStart) {
        return courseRepository.findAllByDateStart(dateStart);
    }

    @Override
    public List<CourseEntity> getAllCourseByDateEnd(LocalDate dateEnd) {
        return courseRepository.findAllByDateEnd(dateEnd);
    }

    @Override
    public CourseEntity getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNoFoundException("error.findCourse"));
    }

    @Override
    public CourseEntity save(CourseEntity entity) {
        try {
            if (entity.getTitleRu() == null || entity.getPrice() == null) {
                throw new ValidationError("error.isEmptyNameAndPrice");
            }

            return courseRepository.save(entity);
        }catch (DataIntegrityViolationException e){
            throw new UniquenessViolationException(e.getMessage());
        }
    }

    @Override
    public List<CourseEntity> getAll() {
        return courseRepository.findAll().stream().filter(courseEntity -> courseEntity.getActive().equals(Active.DELETED)).toList();
    }

    @Override
    public List<CourseEntity> getAllFull(){
        return courseRepository.findAll();
    }

    @Override
    public List<CourseEntity> getAllActive() {
        return courseRepository.findAllByActive(Active.ACTIVE);
    }

    @Override
    public List<CourseEntity> getAllStatus(Active status) {
        return courseRepository.findAllByActive(status);
    }

    @Override
    public void deleteById(Long id) {
        CourseEntity courseEntity = getById(id);
        courseEntity.setActive(Active.DELETED);
        courseRepository.save(courseEntity);
    }
}