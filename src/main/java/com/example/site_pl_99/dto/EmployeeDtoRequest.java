package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Объект запроса для сотрудника")
public class EmployeeDtoRequest {

    @Schema(description = "Полное имя сотрудника",
            example = "Муслимов Муслим Муслимович")
    private String fullName;

    @Schema(description = "Дата рождения сотрудника в формате ГГГГ-ММ-ДД",
            example = "1990-04-25")
    private LocalDate dateBerth;

    @Schema(description = "Фотография сотрудника")
    private ImageDto image;

    @Schema(description = "Название отдела на русском языке",
            example = "Отдел разработки")
    private String departmentRu;

    @Schema(description = "Название отдела на кыргызском языке",
            example = "Иштеп чыгуучу бөлүм")
    private String departmentKg;

    @Schema(description = "Дата приема на работу в формате ГГГГ-ММ-ДД",
            example = "2025-01-10")
    private LocalDate dateEmployment;

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
