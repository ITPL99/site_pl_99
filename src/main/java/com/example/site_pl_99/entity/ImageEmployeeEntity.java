package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.ActiveJob;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "images_employees")
@RequiredArgsConstructor
public class ImageEmployeeEntity extends BaseEntity {
    @Column(name = "file_name", nullable = false ,unique = true)
    private String fileName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", unique = true)
    private EmployeeEntity employeeEntity;
    @Column(name = "active")
    private ActiveJob active;

    public ActiveJob getActive() {
        return active;
    }

    public void setActive(ActiveJob active) {
        this.active = active;
    }

    public String getFileName() {
        return fileName;
    }

    public ImageEmployeeEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public ImageEmployeeEntity setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
        return this;
    }
}
