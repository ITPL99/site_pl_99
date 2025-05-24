package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.Course;
import com.example.site_pl_99.entity.Worker;

import java.time.LocalDateTime;

public class CourseDtoRequestRegister {
    private String title;
    private String description;
    private Long price;
    private String workerName;
    private LocalDateTime dateStarted;
    private LocalDateTime dateEnd;

    public Course tyEntity(){
        return new Course()
                .setTitle(title)
                .setDescription(description)
                .setPrice(price)
                .setDateEnd(dateEnd)
                .setDateStarted(dateStarted);
    }

    public String getTitle() {
        return title;
    }

    public CourseDtoRequestRegister setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseDtoRequestRegister setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public CourseDtoRequestRegister setPrice(Long price) {
        this.price = price;
        return this;
    }

    public String getWorkerName() {
        return workerName;
    }

    public CourseDtoRequestRegister setWorkerName(String workerName) {
        this.workerName = workerName;
        return this;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public CourseDtoRequestRegister setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
        return this;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public CourseDtoRequestRegister setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }
}
