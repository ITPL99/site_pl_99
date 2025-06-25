package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Ответ с данными сотрудника")
public class EmployeeDtoResponse {

    @Schema(description = "Уникальный идентификатор сотрудника", example = "1")
    private Long id;

    @Schema(description = "Полное имя сотрудника", example = "Иванов Иван Иванович")
    private String fullName;

    @Schema(description = "Дата рождения сотрудника", example = "1985-12-15", type = "string", format = "date")
    private LocalDate dateBerth;

    @Schema(description = "Фото сотрудника")
    private ImageDto image;

    @Schema(description = "Название отдела", example = "Отдел маркетинга")
    private String department;

    @Schema(description = "Дата начала работы", example = "2020-03-01", type = "string", format = "date")
    private LocalDate dateEmployment;

    @Schema(description = "Дата увольнения, если применимо", example = "2024-06-01", type = "string", format = "date")
    private LocalDate dateDismissal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateBerth() {
        return dateBerth;
    }

    public void setDateBerth(LocalDate dateBerth) {
        this.dateBerth = dateBerth;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public void setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
    }

    public LocalDate getDateDismissal() {
        return dateDismissal;
    }

    public void setDateDismissal(LocalDate dateDismissal) {
        this.dateDismissal = dateDismissal;
    }
}
