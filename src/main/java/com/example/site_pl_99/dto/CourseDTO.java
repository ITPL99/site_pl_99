package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.Worker;

import java.time.LocalDateTime;

public class CourseDTO { // времно не используется
    private String title;
    private String description;
    private Long price;
    private Worker worker;
    private LocalDateTime dateStarted;
    private LocalDateTime dateEnd;

    public CourseDTO() {
    }

    public CourseDTO(String title,
                     String description,
                     Long price,
                     Worker worker,
                     LocalDateTime dateStarted,
                     LocalDateTime dateEnd) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.worker = worker;
        this.dateStarted = dateStarted;
        this.dateEnd = dateEnd;
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
