package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.ActiveJob;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class EmployeeDtoResponse {
    private Long id;
    private String fullName;
    private LocalDateTime birthDate;
    private String department;
    private ActiveJob active;
    private LocalDateTime dateStarted;
    private LocalDateTime dateFired;
    private Long imageId;

    public Long getId() {
        return id;
    }

    public EmployeeDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public EmployeeDtoResponse setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public EmployeeDtoResponse setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public EmployeeDtoResponse setDepartment(String department) {
        this.department = department;
        return this;
    }

    public ActiveJob getActive() {
        return active;
    }

    public EmployeeDtoResponse setActive(ActiveJob active) {
        this.active = active;
        return this;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public EmployeeDtoResponse setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
        return this;
    }

    public LocalDateTime getDateFired() {
        return dateFired;
    }

    public EmployeeDtoResponse setDateFired(LocalDateTime dateFired) {
        this.dateFired = dateFired;
        return this;
    }

    public Long getImageId() {
        return imageId;
    }

    public EmployeeDtoResponse setImageId(Long imageId) {
        this.imageId = imageId;
        return this;
    }
}
