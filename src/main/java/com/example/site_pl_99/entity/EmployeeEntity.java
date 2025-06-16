package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.ActiveJob;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@RequiredArgsConstructor
public class EmployeeEntity extends BaseEntity {
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;
    @Column(name = "date_birth", nullable = false)
    private LocalDateTime birthDate;
    @Column(name = "department", nullable = false)
    private String department;
    @Column(name = "active")
    private ActiveJob active;
    @Column(name = "date_started")
    private LocalDateTime dateStarted;
    @Column(name = "date_fired")
    private LocalDateTime dateFired;
    @OneToOne(mappedBy = "employeeEntity",fetch = FetchType.EAGER)
    private ImageEmployeeEntity image;

    @PrePersist
    public void prePersist() {
        active = ActiveJob.WORKING;
        dateStarted = LocalDateTime.now();
    }

    public String getFullName() {
        return fullName;
    }

    public EmployeeEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public EmployeeEntity setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public EmployeeEntity setDepartment(String department) {
        this.department = department;
        return this;
    }

    public ActiveJob getActive() {
        return active;
    }

    public EmployeeEntity setActive(ActiveJob active) {
        this.active = active;
        return this;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public EmployeeEntity setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
        return this;
    }

    public LocalDateTime getDateFired() {
        return dateFired;
    }

    public EmployeeEntity setDateFired(LocalDateTime dateFired) {
        this.dateFired = dateFired;
        return this;
    }

    public ImageEmployeeEntity getImage() {
        return image;
    }

    public EmployeeEntity setImage(ImageEmployeeEntity image) {
        this.image = image;
        return this;
    }
}
