package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add-course/{workerId}")
    public ResponseEntity<?> saveCourse(@RequestBody CourseDtoRequest courseDtoRequest, @PathVariable Long workerId) {
        try {
            return ResponseEntity.ok().body(courseService.saveCourse(courseDtoRequest,workerId, (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При сохранении курса произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/get-course-id/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
        try {
            return ResponseEntity.ok().body(courseService.getCourseId(courseId));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При получении курса произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/get-course-all")
    public ResponseEntity<?> getAllCourses() {
        try {
            return ResponseEntity.ok().body(courseService.getAllCourse());
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При получении курса произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/get-course-worker/{workerId}")
    public ResponseEntity<?> getCourseWorker(@PathVariable Long workerId) {
        try {
            return ResponseEntity.ok().body(courseService.getCourseByWorker(workerId));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При получении курса произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/get-course-user/{userId}")
    public ResponseEntity<?> getCourseUser(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok().body(courseService.getCourseByUser(userId));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("При получении курса произошла ошибка: " + e.getMessage());
        }
    }
}
