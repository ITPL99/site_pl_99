package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "course")
@RequiredArgsConstructor
public class CourseEntity extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price")
    private Long price;
    @Column(name = "date_started")
    private LocalDateTime dateStart;
    @Column(name = "date_end")
    private LocalDateTime dateEnd;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private WorkerEntity worker;
    @Column(name = "date_create")
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_create", referencedColumnName = "id")
    private UserEntity userCreated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_updated", referencedColumnName = "id")
    private UserEntity userUpdated;
    @Column(name = "status_deleted")
    private Boolean statusDeleted;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        dateUpdated = LocalDateTime.now();
        statusDeleted = false;
    }

    public String getTitle() {
        return title;
    }

    public CourseEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public CourseEntity setPrice(Long price) {
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

    public WorkerEntity getWorker() {
        return worker;
    }

    public CourseEntity setWorker(WorkerEntity worker) {
        this.worker = worker;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public CourseEntity setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public CourseEntity setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public CourseEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public UserEntity getUserCreated() {
        return userCreated;
    }

    public CourseEntity setUserCreated(UserEntity userCreated) {
        this.userCreated = userCreated;
        return this;
    }

    public UserEntity getUserUpdated() {
        return userUpdated;
    }

    public CourseEntity setUserUpdated(UserEntity userUpdated) {
        this.userUpdated = userUpdated;
        return this;
    }

    public Boolean getStatusDeleted() {
        return statusDeleted;
    }

    public CourseEntity setStatusDeleted(Boolean statusDeleted) {
        this.statusDeleted = statusDeleted;
        return this;
    }
}
