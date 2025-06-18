package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.ActiveJob;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TeacherDtoResponse {
    private Long id;
    private String fullName;
    private LocalDateTime birthday;
    private String linkPortfolio;
    private ActiveJob active;
    private LocalDateTime dateStarted;
    private LocalDateTime dateFired;
    private Long imageId;

    public Long getId() {
        return id;
    }

    public TeacherDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public TeacherDtoResponse setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public TeacherDtoResponse setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getLinkPortfolio() {
        return linkPortfolio;
    }

    public TeacherDtoResponse setLinkPortfolio(String linkPortfolio) {
        this.linkPortfolio = linkPortfolio;
        return this;
    }

    public ActiveJob getActive() {
        return active;
    }

    public TeacherDtoResponse setActive(ActiveJob active) {
        this.active = active;
        return this;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public TeacherDtoResponse setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
        return this;
    }

    public LocalDateTime getDateFired() {
        return dateFired;
    }

    public TeacherDtoResponse setDateFired(LocalDateTime dateFired) {
        this.dateFired = dateFired;
        return this;
    }

    public Long getImageId() {
        return imageId;
    }

    public TeacherDtoResponse setImageId(Long imageId) {
        this.imageId = imageId;
        return this;
    }
}
