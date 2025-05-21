package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDTO;
import com.example.site_pl_99.entity.Course;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.service.CourseService;
import com.example.site_pl_99.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public CourseController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @PostMapping("/create-course/{id}")
    public ResponseEntity<String> createCourse(@RequestBody Course course, @PathVariable Long id){
        UserEntity user = userService.getById(id);
        if (!user.getRoleEntityList().get(0).equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Только Админ может создовать");
        }
        courseService.save(course);
        return ResponseEntity.ok().build();
    }

    @GetMapping("get-course-id/{id}")
    public CourseDTO getCourseById(@PathVariable Long id){
        Course course = courseService.getById(id);
        CourseDTO courseDTO = new CourseDTO()
                .setWorker(course.getWorker())
                .setDateEnd(course.getDateEnd())
                .setDateStarted(course.getDateStarted())
                .setId(course.getId())
                .setPrice(course.getPrice())
                .setDescription(course.getDescription())
                .setTitle(course.getTitle());
        return courseDTO;
    }

    @GetMapping("get-all-courses")
    public List<CourseDTO> getAll(){
        List<Course> courses =courseService.getAll();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for(Course course : courses){
            CourseDTO courseDTO = new CourseDTO()
                    .setWorker(course.getWorker())
                    .setDateEnd(course.getDateEnd())
                    .setDateStarted(course.getDateStarted())
                    .setId(course.getId())
                    .setPrice(course.getPrice())
                    .setDescription(course.getDescription())
                    .setTitle(course.getTitle());
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

}
