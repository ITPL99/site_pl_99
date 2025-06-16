package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.ActiveJob;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MasterDtoResponse {
    private Long id;
    private String fullName;
    private LocalDateTime birthday;
    private String profession;
    private ActiveJob active;
    private LocalDateTime dateStarted;
    private LocalDateTime dateFired;
    private Long imageId;

    public Long getId() {
        return id;
    }

    public MasterDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public MasterDtoResponse setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public MasterDtoResponse setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getProfession() {
        return profession;
    }

    public MasterDtoResponse setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public ActiveJob getActive() {
        return active;
    }

    public MasterDtoResponse setActive(ActiveJob active) {
        this.active = active;
        return this;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public MasterDtoResponse setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
        return this;
    }

    public LocalDateTime getDateFired() {
        return dateFired;
    }

    public MasterDtoResponse setDateFired(LocalDateTime dateFired) {
        this.dateFired = dateFired;
        return this;
    }

    public Long getImageId() {
        return imageId;
    }

    public MasterDtoResponse setImageId(Long imageId) {
        this.imageId = imageId;
        return this;
    }
}
