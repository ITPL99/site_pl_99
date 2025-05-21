package com.example.site_pl_99.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "qualifications")
public class Qualification extends BaseEntity {
    @Column
    private String title;
    @Column
    private String description;
    @ManyToMany(mappedBy = "qualificationWorkerList",fetch = FetchType.LAZY)
    private List<Worker> workers;

    public Qualification() {
    }

    public Qualification(
            List<Worker> workers,
            Long id,
            String title,
            String description) {
        this.workers = workers;
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public Qualification setWorkers(List<Worker> workers) {
        this.workers = workers;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Qualification setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Qualification setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Qualification setDescription(String description) {
        this.description = description;
        return this;
    }
}