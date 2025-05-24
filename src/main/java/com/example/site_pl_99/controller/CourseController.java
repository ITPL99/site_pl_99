package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDTO;
import com.example.site_pl_99.dto.CourseDtoRequestRegister;
import com.example.site_pl_99.entity.Course;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.service.CourseService;
import com.example.site_pl_99.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/create-course")
    public ResponseEntity<String> createCourse(@RequestBody CourseDtoRequestRegister courseDtoRequestRegister) {
        courseService.save(courseDtoRequestRegister);
        return ResponseEntity.ok("Вы создали курс с тайтлом: " + courseDtoRequestRegister.getTitle());
    }

    @GetMapping("/get-course-id/{id}")
    public CourseDTO getCourseById(@PathVariable Long id){
        return new CourseDTO(courseService.getById(id));
    }

    @GetMapping("/get-all-courses")
    public List<CourseDTO> getAll(){
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for(Course course : courseService.getAll()){
            CourseDTO courseDTO = new CourseDTO(course);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

}
