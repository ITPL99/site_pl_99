package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDtoAll;
import com.example.site_pl_99.dto.CourseDtoResponse;
import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.enums.CourseType;

import com.example.site_pl_99.mapper.CourseMapper;
import com.example.site_pl_99.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController  {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    public ResponseEntity<CourseDtoResponse> getByTitle(String title) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.getByTitle(title)));
    }


    public ResponseEntity<List<CourseDtoAll>> getAllCourseByType(CourseType type) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<List<CourseDtoAll>> getAllCourseByPrice(Double price) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<List<CourseDtoAll>> getAllCourseByDateStart(LocalDate dateStart) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<List<CourseDtoAll>> getAllCourseByDateEnd(LocalDate dateEnd) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<CourseDtoResponse> getById(Long id) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<CourseDtoResponse> save(CourseDtoRequest entity) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<List<CourseDtoAll>> getAll() {
        return ResponseEntity.ok(null);
    }

    public void deleteById(Long id) {

    }
}
