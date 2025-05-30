package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workers")
@RequiredArgsConstructor
public class WorkerEntity extends BaseEntity {
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "date")
    private LocalDate dateBirth;
    @Column(name = "bio")
    private String biography;
    @Column(name = "profession")
    private String profession;
    @Column(name = "date_create")
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @Column(name = "status_deleted")
    private Boolean statusDeleted;

    @OneToOne(mappedBy = "worker",fetch = FetchType.EAGER)
    private AvatarWorkerEntity avatar;
    @OneToMany(mappedBy = "worker",fetch = FetchType.EAGER)
    private List<CourseEntity> courseEntityList;
    @ManyToMany(mappedBy = "workerEntities", fetch = FetchType.EAGER)
    private List<QualificationEntity> qualificationEntities;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        dateUpdated = LocalDateTime.now();
        statusDeleted = false;
    }

    public String getFullName() {
        return fullName;
    }

    public WorkerEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public WorkerEntity setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
        return this;
    }

    public String getBiography() {
        return biography;
    }

    public WorkerEntity setBiography(String biography) {
        this.biography = biography;
        return this;
    }

    public String getProfession() {
        return profession;
    }

    public WorkerEntity setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public WorkerEntity setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public WorkerEntity setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public WorkerEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Boolean getStatusDeleted() {
        return statusDeleted;
    }

    public WorkerEntity setStatusDeleted(Boolean statusDeleted) {
        this.statusDeleted = statusDeleted;
        return this;
    }

    public AvatarWorkerEntity getAvatar() {
        return avatar;
    }

    public WorkerEntity setAvatar(AvatarWorkerEntity avatar) {
        this.avatar = avatar;
        return this;
    }

    public List<CourseEntity> getCourseEntityList() {
        return courseEntityList;
    }

    public WorkerEntity setCourseEntityList(List<CourseEntity> courseEntityList) {
        this.courseEntityList = courseEntityList;
        return this;
    }

    public List<QualificationEntity> getQualificationEntities() {
        return qualificationEntities;
    }

    public WorkerEntity setQualificationEntities(List<QualificationEntity> qualificationEntities) {
        this.qualificationEntities = qualificationEntities;
        return this;
    }

    @Override
    public String toString() {
        return "WorkerEntity{" +
                "fullName='" + fullName + '\'' +
                ", dateBirth=" + dateBirth +
                ", biography='" + biography + '\'' +
                ", profession='" + profession + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", user=" + user +
                ", statusDeleted=" + statusDeleted +
                ", avatar=" + avatar +
                ", courseEntityList=" + courseEntityList +
                ", qualificationEntities=" + qualificationEntities +
                '}';
    }
}
