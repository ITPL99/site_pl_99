package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.ActiveJob;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "teachers")
@RequiredArgsConstructor
public class TeacherEntity extends BaseEntity {
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;
    @Column(name = "date_birth", nullable = false)
    private LocalDateTime birthday;
    @Column(name = "link_portfolio", nullable = false)
    private String linkPortfolio;
    @Column(name = "active")
    private ActiveJob active;
    @Column(name = "date_started")
    private LocalDateTime dateStarted;
    @Column(name = "date_fired")
    private LocalDateTime dateFired;
    @OneToOne(mappedBy = "teacher", fetch = FetchType.EAGER)
    private ImageTeacherEntity image;

    @PrePersist
    public void prePersist() {
        active = ActiveJob.WORKING;
        dateStarted = LocalDateTime.now();
    }

    public String getFullName() {
        return fullName;
    }

    public TeacherEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public TeacherEntity setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getLinkPortfolio() {
        return linkPortfolio;
    }

    public TeacherEntity setLinkPortfolio(String linkPortfolio) {
        this.linkPortfolio = linkPortfolio;
        return this;
    }

    public ActiveJob getActive() {
        return active;
    }

    public TeacherEntity setActive(ActiveJob active) {
        this.active = active;
        return this;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public TeacherEntity setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
        return this;
    }

    public LocalDateTime getDateFired() {
        return dateFired;
    }

    public TeacherEntity setDateFired(LocalDateTime dateFired) {
        this.dateFired = dateFired;
        return this;
    }

    public ImageTeacherEntity getImage() {
        return image;
    }

    public TeacherEntity setImage(ImageTeacherEntity image) {
        this.image = image;
        return this;
    }
}
