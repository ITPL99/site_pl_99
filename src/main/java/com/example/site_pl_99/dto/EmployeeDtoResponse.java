package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Объект ответа с информацией о сотруднике")
public class EmployeeDtoResponse {

    @Schema(description = "Уникальный идентификатор сотрудника",
            example = "1")
    private Long id;

    @Schema(description = "Полное имя сотрудника",
            example = "Азиретов Азирет Азиретович")
    private String fullName;

    @Schema(description = "Дата рождения сотрудника в формате ГГГГ-ММ-ДД",
            example = "1995-08-12")
    private LocalDate dateBerth;

    @Schema(description = "Имя файла изображения сотрудника",
            example = "employee_123.jpg")
    private String imageFileName;

    @Schema(description = "Название отдела, в котором работает сотрудник",
            example = "IT-отдел")
    private String department;

    @Schema(description = "Дата начала работы сотрудника",
            example = "2025-06-15")
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