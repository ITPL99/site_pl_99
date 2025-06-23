package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.enums.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    Optional<List<CourseEntity>> findAllCoursesByTitleRuOrTitleKg(String titleRu, String titleKg);
    Optional<List<CourseEntity>> findAllByType(CourseType type);
    Optional<List<CourseEntity>> findAllByPrice(Double price);
    Optional<List<CourseEntity>> findAllByDateStart(LocalDate dateStart);
    Optional<List<CourseEntity>> findAllByDateEnd(LocalDate dateEnd);
    Optional<List<CourseEntity>> findAllByActive(Active active);
}
