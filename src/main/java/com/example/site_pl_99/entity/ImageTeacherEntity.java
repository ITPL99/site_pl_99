package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.ActiveJob;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "images_teachers")
@RequiredArgsConstructor
public class ImageTeacherEntity extends BaseEntity {
    private String fileName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", unique = true)
    private TeacherEntity teacher;
    @Column(name = "active")
    private ActiveJob active;

    public ActiveJob getActive() {
        return active;
    }

    public void setActive(ActiveJob active) {
        this.active = active;
    }

    public String getFileName() {
        return fileName;
    }

    public ImageTeacherEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public ImageTeacherEntity setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
        return this;
    }
}
