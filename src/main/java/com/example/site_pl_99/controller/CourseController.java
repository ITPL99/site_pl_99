package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.CourseMapper;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.service.CourseService;
import com.example.site_pl_99.utils.Internalization;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    private final AuthService authService;

    public CourseController(CourseService courseService, AuthService authService) {
        this.courseService = courseService;
        this.authService = authService;
    }

    @PostMapping("/add-course")
    public ResponseEntity<?> addNewCourse(@RequestBody CourseDtoRequest courseDtoRequest, @RequestParam Long workerId) throws BaseException {
            return ResponseEntity.ok(CourseMapper.toCourseDtoResponse(courseService.saveCourse(courseDtoRequest, workerId, authService.getCurrentUser())));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) throws BaseException {
            return ResponseEntity.ok(CourseMapper.toCourseDtoResponse(courseService.getCourseId(id)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCourses() throws BaseException {
            return ResponseEntity.ok(CourseMapper.toCourseDtoRequestList(courseService.getAllCourse()));
    }

    @GetMapping("/get-by-user/{id}")
    public ResponseEntity<?> getByUser(@PathVariable Long id) throws BaseException {
            return ResponseEntity.ok(CourseMapper.toCourseDtoRequestList(courseService.getCourseByUser(id)));
    }
}
