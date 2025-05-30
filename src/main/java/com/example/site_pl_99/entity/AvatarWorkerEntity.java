package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "avatars_workers")
@RequiredArgsConstructor
public class AvatarWorkerEntity extends BaseEntity {
    @Column(name = "file_name", nullable = false, unique = true)
    private String fileName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private WorkerEntity worker;
    @Column(name = "date_upload")
    private LocalDateTime dateUpload;
    @Column(name = "date_update")
    private LocalDateTime dateUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @Column(name = "status_deleted")
    private Boolean statusDeleted;

    @PrePersist
    public void prePersist() {
        dateUpload = LocalDateTime.now();
        dateUpdated = LocalDateTime.now();
        statusDeleted = false;
    }

    public String getFileName() {
        return fileName;
    }

    public AvatarWorkerEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public WorkerEntity getWorker() {
        return worker;
    }

    public AvatarWorkerEntity setWorker(WorkerEntity worker) {
        this.worker = worker;
        return this;
    }

    public LocalDateTime getDateUpload() {
        return dateUpload;
    }

    public AvatarWorkerEntity setDateUpload(LocalDateTime dateUpload) {
        this.dateUpload = dateUpload;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public AvatarWorkerEntity setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public AvatarWorkerEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Boolean getStatusDeleted() {
        return statusDeleted;
    }

    public AvatarWorkerEntity setStatusDeleted(Boolean statusDeleted) {
        this.statusDeleted = statusDeleted;
        return this;
    }
}
