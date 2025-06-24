package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "DTO запроса на обновление данных сотрудника")
public class EmployeeDtoRequestUpdate {

    @Schema(description = "Уникальный идентификатор сотрудника", example = "1", required = true)
    private Long id;

    @Schema(description = "Полное имя сотрудника", example = "Иванов Иван Иванович")
    private String fullName;

    @Schema(description = "Дата рождения сотрудника", example = "1980-07-15", type = "string", format = "date")
    private LocalDate dateBerth;

    @Schema(description = "Фотография сотрудника (DTO изображения)")
    private ImageDto image;

    @Schema(description = "Название отдела на русском языке", example = "Отдел кадров")
    private String departmentRu;

    @Schema(description = "Название отдела на кыргызском языке", example = "Кадрлар бөлүмү")
    private String departmentKg;

    @Schema(description = "Дата начала работы", example = "2022-01-10", type = "string", format = "date")
    private LocalDate dateEmployment;

    @Schema(description = "Дата увольнения (если уволен)", example = "2024-06-01", type = "string", format = "date")
    private LocalDate dateDismissal;

    public LocalDate getDateDismissal() {
        return dateDismissal;
    }

    public void setDateDismissal(LocalDate dateDismissal) {
        this.dateDismissal = dateDismissal;
    }

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

    public String getDepartmentRu() {
        return departmentRu;
    }

    public void setDepartmentRu(String departmentRu) {
        this.departmentRu = departmentRu;
    }

    public String getDepartmentKg() {
        return departmentKg;
    }

    public void setDepartmentKg(String departmentKg) {
        this.departmentKg = departmentKg;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public void setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
    }
}
