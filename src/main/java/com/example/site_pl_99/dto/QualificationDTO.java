package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.Worker;

import java.util.List;

public class QualificationDTO {
    private Long id;
    private String title;
    private String description;
    private Worker workers;

    public QualificationDTO() {
    }

    public QualificationDTO(Long id,
                            String title,
                            String description,
                            Worker workers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.workers = workers;
    }

    public Long getId() {
        return id;
    }

    public QualificationDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public QualificationDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QualificationDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Worker getWorkers() {
        return workers;
    }

    public QualificationDTO setWorkers(Worker workers) {
        this.workers = workers;
        return this;
    }
}
