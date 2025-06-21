package com.example.site_pl_99.dto;

import java.time.LocalDate;

public class EmployeeDtoResponse {
    private Long id;
    private String fullName;
    private LocalDate dateBerth;
    private String imageFileName;
    private String department;
    private LocalDate dateEmployment;

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

    public LocalDate getDateBerth() {
        return dateBerth;
    }

    public EmployeeDtoResponse setDateBerth(LocalDate dateBerth) {
        this.dateBerth = dateBerth;
        return this;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public EmployeeDtoResponse setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public EmployeeDtoResponse setDepartment(String department) {
        this.department = department;
        return this;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public EmployeeDtoResponse setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
        return this;
    }
}
