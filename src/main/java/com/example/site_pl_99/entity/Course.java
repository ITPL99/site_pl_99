package com.example.site_pl_99.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Long price;
    @Column
    private LocalDateTime dateStarted;
    @Column
    private LocalDateTime dateEnd;
    @OneToOne
    private Worker worker;

    public Course() {
    }

    public Course(Long id,
                  String title,
                  String description,
                  Long price,
                  LocalDateTime dateStarted,
                  LocalDateTime dateEnd,
                  Worker worker) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.dateStarted = dateStarted;
        this.dateEnd = dateEnd;
        this.worker = worker;
    }

    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Course setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Course setPrice(Long price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public Course setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
        return this;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public Course setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public Worker getWorker() {
        return worker;
    }

    public Course setWorker(Worker worker) {
        this.worker = worker;
        return this;
    }
}
