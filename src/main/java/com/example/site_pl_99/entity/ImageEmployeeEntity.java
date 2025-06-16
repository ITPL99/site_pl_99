package com.example.site_pl_99.entity;

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
