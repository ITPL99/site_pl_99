package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.enums.CourseType;

import com.example.site_pl_99.mapper.CourseMapper;
import com.example.site_pl_99.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/course")
public class CourseController  {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/get-by-title")
    public ResponseEntity<?> getByTitle(@RequestParam String title) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.getByTitle(title)));
    }

    @GetMapping("/get-all-by-type")
    public ResponseEntity<List<?>> getAllCourseByType(@RequestParam CourseType type) {
        return ResponseEntity.ok(courseService.getAllCourseByType(type).stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
    }

    @GetMapping("/get-all-by-price")
    public ResponseEntity<List<?>> getAllCourseByPrice(@RequestParam Double price) {
        return ResponseEntity.ok(courseService.getAllCourseByPrice(price).stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
    }

    @GetMapping("/get-all-by-date-start")
    public ResponseEntity<List<?>> getAllCourseByDateStart(@RequestParam LocalDate dateStart) {
        return ResponseEntity.ok(courseService.getAllCourseByDateStart(dateStart).stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
    }

    @GetMapping("/get-all-by-date-end")
    public ResponseEntity<List<?>> getAllCourseByDateEnd(@RequestParam LocalDate dateEnd) {
        return ResponseEntity.ok(courseService.getAllCourseByDateEnd(dateEnd).stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.getById(id)));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CourseDtoRequest entity) {
        return ResponseEntity.ok(CourseMapper.mapEntityToDtoResponse(courseService.save(CourseMapper.toEntity(entity))));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(courseService.getAll().stream().map(CourseMapper::mapEntityToDtoResponse).collect(toList()));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        courseService.deleteById(id);
    }
}
