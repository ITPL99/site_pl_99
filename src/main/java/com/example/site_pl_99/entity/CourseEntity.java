package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.CourseType;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "course")
@RequiredArgsConstructor
public class CourseEntity extends BaseEntity {
    @Column(name = "type", nullable = false)
    private CourseType courseType;
    @Column(name = "title_ru", nullable = false)
    private String titleRu;
    @Column(name = "title_kg", nullable = false)
    private String titleKg;
    @Column(name = "description_ru", nullable = false)
    private String descriptionRu;
    @Column(name = "description_kg", nullable = false)
    private String descriptionKg;
    @Column(name = "price", nullable = false)
    private Integer price;
    @Column(name = "date_started", nullable = false)
    private LocalDateTime dateStart;
    @Column(name = "date_end", nullable = false)
    private LocalDateTime dateEnd;
    @OneToOne(mappedBy = "courseEntity", fetch = FetchType.EAGER)
    private ImageCourseEntity imageCourse;

    public CourseType getCourseType() {
        return courseType;
    }

    public CourseEntity setCourseType(CourseType courseType) {
        this.courseType = courseType;
        return this;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public CourseEntity setTitleRu(String titleRu) {
        this.titleRu = titleRu;
        return this;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public CourseEntity setTitleKg(String titleKg) {
        this.titleKg = titleKg;
        return this;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public CourseEntity setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
        return this;
    }

    public String getDescriptionKg() {
        return descriptionKg;
    }

    public CourseEntity setDescriptionKg(String descriptionKg) {
        this.descriptionKg = descriptionKg;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public CourseEntity setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public CourseEntity setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public CourseEntity setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public ImageCourseEntity getImageCourse() {
        return imageCourse;
    }

    public CourseEntity setImageCourse(ImageCourseEntity imageCourse) {
        this.imageCourse = imageCourse;
        return this;
    }
}
