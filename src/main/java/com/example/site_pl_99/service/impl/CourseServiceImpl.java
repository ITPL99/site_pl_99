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
        return courseRepository.findAllByType(type).stream().filter(entity -> entity.getActive().equals(Active.DELETED)).toList();
    }

    @Override
    public List<CourseEntity> getAllCourseByPrice(Double price) {
        return courseRepository.findAllByPrice(price).stream().filter(entity -> entity.getActive().equals(Active.DELETED)).toList();
    }

    @Override
    public List<CourseEntity> getAllCourseByDateStart(LocalDate dateStart) {
        return courseRepository.findAllByDateStart(dateStart).stream().filter(entity -> entity.getActive().equals(Active.DELETED)).toList();
    }

    @Override
    public List<CourseEntity> getAllCourseByDateEnd(LocalDate dateEnd) {
        return courseRepository.findAllByDateEnd(dateEnd).stream().filter(entity -> entity.getActive().equals(Active.DELETED)).toList();
    }

    @Override
    public CourseEntity getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNoFoundException("error.findCourse"));
    }

    @Override
    public CourseEntity save(CourseEntity entity) {
        try {
            if(entity.getType() == null){
                throw new ValidationError("error.typeNull");
            }if (entity.getTitleRu() == null || entity.getTitleRu().isBlank()){
                throw new ValidationError("error.titleNull");
            }if (entity.getTitleKg() == null || entity.getTitleKg().isBlank() ){
                throw new ValidationError("error.titleNull");
            }if (entity.getImage() == null){
                throw new ValidationError("error.ImageNull");
            }if (entity.getPrice() == null || entity.getPrice() <= 0) {
                throw new ValidationError("error.priceNull");
            }
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
        return courseRepository.findAll().stream().filter(entity -> entity.getActive().equals(Active.DELETED)).toList();
    }

    @Override
    public List<CourseEntity> getAllFull(){
        return courseRepository.findAll().stream().filter(entity -> entity.getActive().equals(Active.DELETED)).toList();
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