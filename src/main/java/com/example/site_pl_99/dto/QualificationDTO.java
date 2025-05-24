package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.Qualification;
import com.example.site_pl_99.entity.Worker;

import java.util.List;

public class QualificationDTO {
    private Long id;
    private String title;
    private String description;

    public QualificationDTO() {
    }

    public QualificationDTO(Qualification qualification) {
        this.id = qualification.getId();
        this.title = qualification.getTitle();
        this.description = qualification.getDescription();
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

}
