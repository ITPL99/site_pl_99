package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class EmployeeDtoRequest {
    private String fullName;
    private LocalDateTime birthDate;
    private String department;

    public String getFullName() {
        return fullName;
    }

    public EmployeeDtoRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public EmployeeDtoRequest setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public EmployeeDtoRequest setDepartment(String department) {
        this.department = department;
        return this;
    }
}
