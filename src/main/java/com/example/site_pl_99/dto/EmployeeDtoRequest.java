package com.example.site_pl_99.dto;



import java.time.LocalDate;

public class EmployeeDtoRequest {
    private String fullName;
    private LocalDate dateBerth;
    private ImageDto image;
    private String departmentRu;
    private String departmentKg;
    private LocalDate dateEmployment;
    private LocalDate dateDismissal;

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

    public LocalDate getDateDismissal() {
        return dateDismissal;
    }

    public EmployeeDtoRequest setDateDismissal(LocalDate dateDismissal) {
        this.dateDismissal = dateDismissal;
        return this;
    }
}
