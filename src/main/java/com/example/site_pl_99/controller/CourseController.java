package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDtoAll;
import com.example.site_pl_99.dto.CourseDtoResponseRu;
import com.example.site_pl_99.dto.CourseDtoRequest;
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

    @GetMapping("/get-by-title")
    public ResponseEntity<CourseDtoResponseRu> getByTitle(@RequestParam String title) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.getByTitle(title)));
    }

    @GetMapping("/get-all-by-type")
    public ResponseEntity<List<CourseDtoResponseRu>> getAllCourseByType(CourseType type) {
        return ResponseEntity.ok(courseService.getAllCourseByType(type).stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
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

    public ResponseEntity<CourseDtoResponseRu> getById(Long id) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<CourseDtoResponseRu> save(CourseDtoRequest entity) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<List<CourseDtoAll>> getAll() {
        return ResponseEntity.ok(null);
    }

    public void deleteById(Long id) {

    }
}
