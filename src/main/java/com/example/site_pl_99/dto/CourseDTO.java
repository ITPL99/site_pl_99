package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.Course;
import com.example.site_pl_99.entity.Worker;

import java.time.LocalDateTime;

public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private Worker worker;
    private LocalDateTime dateStarted;
    private LocalDateTime dateEnd;

    public CourseDTO() {
    }

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.price = course.getPrice();
        this.dateStarted = course.getDateStarted();
        this.dateEnd = course.getDateEnd();
        this.worker = course.getWorker();
    }

    public Long getId() {
        return id;
    }

    public CourseDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public CourseDTO setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Worker getWorker() {
        return worker;
    }

    public CourseDTO setWorker(Worker worker) {
        this.worker = worker;
        return this;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public CourseDTO setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
        return this;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public CourseDTO setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }
}
