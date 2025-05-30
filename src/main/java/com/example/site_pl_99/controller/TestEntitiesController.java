package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.*;
import com.example.site_pl_99.entity.*;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.*;
import com.example.site_pl_99.repository.*;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.utils.Internalization;
import jakarta.persistence.PrePersist;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/test")
public class TestEntitiesController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final NewsRepository newsRepository;
    private final AvatarRepository avatarRepository;
    private final QualificationRepository qualificationRepository;
    private final WorkerRepository workerRepository;
    private final Internalization internalization;

    public TestEntitiesController(UserService userService, UserRepository userRepository, CourseRepository courseRepository, NewsRepository newsRepository, AvatarRepository avatarRepository, QualificationRepository qualificationRepository, WorkerRepository workerRepository, Internalization internalization) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.newsRepository = newsRepository;
        this.avatarRepository = avatarRepository;
        this.qualificationRepository = qualificationRepository;
        this.workerRepository = workerRepository;
        this.internalization = internalization;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewUser(@RequestBody UserDtoRequestRegister userDtoRequestRegister, @RequestHeader(name = "Accept-Language", required = false) Locale language) {
        try{
            System.out.println(userDtoRequestRegister.getRoles().toString());
            return ResponseEntity.ok(UserMapper.toUserDtoResponse(userService.save(userDtoRequestRegister)));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), language));
        }
    }

    @PostMapping("/add-news/{id}")
    public ResponseEntity<?> addNews(@RequestBody NewsDtoRequest newsDtoRequest, @PathVariable("id") Long userId){
        try{
            UserEntity userEntity = userRepository.findById(userId).orElseThrow();
            ImageNewsEntity imageNewsEntity = new ImageNewsEntity().setFileName("feroerge123").setUserCreated(userEntity).setUserUpdated(userEntity);
            VideoNewsEntity videoNewsEntity = new VideoNewsEntity().setFileName("feroerge123").setUserCreated(userEntity).setUserUpdated(userEntity);
            return ResponseEntity.ok(NewsMapper.toNewsDtoResponse(newsRepository.save(NewsMapper.toNewsEntity(newsDtoRequest, userEntity, List.of(imageNewsEntity), List.of(videoNewsEntity)))));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), Locale.ENGLISH));
        }
    }

    @PostMapping("/get-news")
    public ResponseEntity<?> getNews(@RequestHeader(name = "Accept-Language", required = false) Locale language){
        try{
            return ResponseEntity.ok(NewsMapper.toNewsDtoResponseList(newsRepository.findAll()));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), language));
        }
    }

    @PostMapping("/add-worker/{id}")
    public ResponseEntity<?> addNewWorker(@RequestBody WorkerDtoRequest workerDtoRequest, @PathVariable("id") Long userId){
        try{
            UserEntity userEntity = userRepository.findById(userId).orElseThrow();
            WorkerEntity workerEntity = WorkerMapper.toWorkerEntity(workerDtoRequest, userEntity);
            workerRepository.save(workerEntity);
            return ResponseEntity.ok("saved worker");
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), Locale.ENGLISH));
        }
    }

    @PostMapping("/upload-avatar/{id}")
    public ResponseEntity<?> uploadAvatar(@RequestBody AvatarDtoRequest avatarDtoRequest, @PathVariable("id") Long userId, @RequestParam Long workerId){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        WorkerEntity workerEntity = workerRepository.findById(workerId).orElseThrow();
        AvatarWorkerEntity avatarWorkerEntity = AvatarMapper.toAvatarWorkerEntity(avatarDtoRequest).setUser(userEntity).setWorker(workerEntity);
        return ResponseEntity.ok(AvatarMapper.toAvatarDtoResponse(avatarRepository.save(avatarWorkerEntity)));
    }

    @PostMapping("/get-all-avatars")
    public ResponseEntity<?> getAllAvatars(@RequestHeader(name = "Accept-Language", required = false) Locale language){
        try{
            return ResponseEntity.ok(AvatarMapper.toAvatarDtoResponseList(avatarRepository.findAll()));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), language));
        }
    }



    @PostMapping("/get-workers")
    public ResponseEntity<?> getWorkers(@RequestHeader(name = "Accept-Language", required = false) Locale language){
        try{
            return ResponseEntity.ok(WorkerMapper.toWorkerDtoResponseList(workerRepository.findAll()));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), language));
        }
    }

    @PostMapping("/add-course/{id}")
    public ResponseEntity<?> addNewCourse(@RequestBody CourseDtoRequest courseDtoRequest, @PathVariable("id") Long userId, @RequestParam Long workerId){
        try{
            UserEntity userEntity = userRepository.findById(userId).orElseThrow();
            WorkerEntity workerEntity = workerRepository.findById(workerId).orElseThrow();
            return ResponseEntity.ok(CourseMapper.toCourseDtoResponse(courseRepository.save(CourseMapper.toCourseEntity(courseDtoRequest, userEntity, workerEntity))));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), Locale.ENGLISH));
        }
    }

    @PostMapping("get-courses")
    public ResponseEntity<?> getCourses(@RequestHeader(name = "Accept-Language", required = false) Locale language){
        try{
            return ResponseEntity.ok(CourseMapper.toCourseDtoRequestList(courseRepository.findAll()));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), language));
        }
    }

    @PostMapping("/add-qualification/{id}")
    public ResponseEntity<?> addNewQualification(@RequestBody QualificationDtoRequest qualificationDtoRequest, @PathVariable("id") Long userId){
        try{
            UserEntity userEntity = userRepository.findById(userId).orElseThrow();
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponse(qualificationRepository.save(QualificationMapper.toQualificationEntity(qualificationDtoRequest, userEntity))));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), Locale.ENGLISH));
        }
    }

    @PostMapping("/get-qualifications")
    public ResponseEntity<?> getQualifications(@RequestHeader(name = "Accept-Language", required = false) Locale language){
        try{
            return ResponseEntity.ok(QualificationMapper.toQualificationDtoResponseList(qualificationRepository.findAll()));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), language));
        }
    }
}
