package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "qualifications")
@RequiredArgsConstructor
public class QualificationEntity extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "status_deleted")
    private Boolean statusDeleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @Column(name = "date_create")
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "m2m_qualifications_workers",
            joinColumns = @JoinColumn(name = "qualification_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id", referencedColumnName = "id", unique = true))
    private List<WorkerEntity> workerEntities;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        dateUpdated = LocalDateTime.now();
        statusDeleted = false;
    }

    public String getTitle() {
        return title;
    }

    public QualificationEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QualificationEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getStatusDeleted() {
        return statusDeleted;
    }

    public QualificationEntity setStatusDeleted(Boolean statusDeleted) {
        this.statusDeleted = statusDeleted;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public QualificationEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public QualificationEntity setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public QualificationEntity setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public List<WorkerEntity> getWorkerEntities() {
        return workerEntities;
    }

    public QualificationEntity setWorkerEntities(List<WorkerEntity> workerEntities) {
        this.workerEntities = workerEntities;
        return this;
    }
}
