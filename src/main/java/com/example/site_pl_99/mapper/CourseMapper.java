package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.dto.CourseDtoResponse;
import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.WorkerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {

    public static CourseEntity toCourseEntity(CourseDtoRequest courseDtoRequest, UserEntity userEntity, WorkerEntity workerEntity) {
        return new CourseEntity().setTitle(courseDtoRequest.getTitle())
                .setDescription(courseDtoRequest.getDescription())
                .setPrice(courseDtoRequest.getPrice())
                .setDateStart(courseDtoRequest.getDateStart())
                .setDateEnd(courseDtoRequest.getDateEnd())
                .setWorker(workerEntity)
                .setUser(userEntity)
                .setUserCreated(userEntity)
                .setUserUpdated(userEntity);
    }

    public static CourseDtoResponse toCourseDtoResponse(CourseEntity courseEntity) {
        return new CourseDtoResponse().setId(courseEntity.getId())
                .setTitle(courseEntity.getTitle())
                .setDescription(courseEntity.getDescription())
                .setPrice(courseEntity.getPrice())
                .setDateStart(courseEntity.getDateStart())
                .setDateEnd(courseEntity.getDateEnd())
                .setWorkerId(courseEntity.getWorker().getId())
                .setDateCreated(courseEntity.getDateCreated())
                .setDateUpdated(courseEntity.getDateUpdated())
                .setUserId(courseEntity.getUser().getId())
                .setUserIdUpdated(courseEntity.getUserUpdated().getId());
    }

    public static List<CourseDtoResponse> toCourseDtoRequestList(List<CourseEntity> courseEntityList) {
        return courseEntityList.stream().map(CourseMapper::toCourseDtoResponse).collect(Collectors.toList());
    }
}
