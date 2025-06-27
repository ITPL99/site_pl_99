package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Запрос на создание или обновление сотрудника")
public class EmployeeDtoRequest {

    @Schema(description = "Полное имя сотрудника", example = "Иванов Иван Иванович")
    private String fullName;

    @Schema(description = "Дата рождения сотрудника", example = "1985-05-20", type = "string", format = "date")
    private LocalDate dateBerth;

    @Schema(description = "Фотография сотрудника (объект изображения)")
    private ImageDto image;

    @Schema(description = "Название отдела на русском языке", example = "Отдел маркетинга")
    private String departmentRu;

    @Schema(description = "Название отдела на кыргызском языке", example = "Маркетинг бөлүмү")
    private String departmentKg;

    @Schema(description = "Дата приёма на работу", example = "2020-01-15", type = "string", format = "date")
    private LocalDate dateEmployment;

    @Schema(description = "Дата увольнения (если применимо)", example = "2024-06-01", type = "string", format = "date")
    private LocalDate dateDismissal;


    public LocalDate getDateDismissal() {
        return dateDismissal;
    }

    public void setDateDismissal(LocalDate dateDismissal) {
        this.dateDismissal = dateDismissal;
    }

    public String getFullName() {
        return fullName;
    }

    public EmployeeDtoRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDate getDateBerth() {
        return dateBerth;
    }

    public EmployeeDtoRequest setDateBerth(LocalDate dateBerth) {
        this.dateBerth = dateBerth;
        return this;
    }

    public ImageDto getImage() {
        return image;
    }

    public EmployeeDtoRequest setImage(ImageDto image) {
        this.image = image;
        return this;
    }

    public String getDepartmentRu() {
        return departmentRu;
    }

    public EmployeeDtoRequest setDepartmentRu(String departmentRu) {
        this.departmentRu = departmentRu;
        return this;
    }

    public String getDepartmentKg() {
        return departmentKg;
    }

    public EmployeeDtoRequest setDepartmentKg(String departmentKg) {
        this.departmentKg = departmentKg;
        return this;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public EmployeeDtoRequest setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
        return this;
    }


}
