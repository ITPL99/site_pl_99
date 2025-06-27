package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.enums.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findAllCoursesByTitleRuOrTitleKg(String titleRu, String titleKg);
    List<CourseEntity> findAllByType(CourseType type);
    List<CourseEntity> findAllByPrice(Double price);
    List<CourseEntity> findAllByDateStart(LocalDate dateStart);
    List<CourseEntity> findAllByDateEnd(LocalDate dateEnd);
    List<CourseEntity> findAllByActive(Active active);
}
