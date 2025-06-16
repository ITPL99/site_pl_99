package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "images_courses")
@RequiredArgsConstructor
public class ImageCourseEntity extends BaseEntity {
    @Column(name = "file_name", nullable = false, unique = true)
    private String fileName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", unique = true)
    private CourseEntity courseEntity;

    public String getFileName() {
        return fileName;
    }

    public ImageCourseEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public ImageCourseEntity setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
        return this;
    }
}
