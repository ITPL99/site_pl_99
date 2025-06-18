package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.Active;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity{
    private String fullName;
    private LocalDate dateBerth;
    @OneToOne
    @JoinColumn(name = "image_id",referencedColumnName = "id")
    private ImageEntity image;
    private String departmentRu;
    private String departmentKg;
    @Enumerated(EnumType.STRING)
    private Active active;
    private LocalDate dateEmployment;
    private LocalDate dateDismissal;

    public String getFullName() {
        return fullName;
    }

    public EmployeeEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDate getDateBerth() {
        return dateBerth;
    }

    public EmployeeEntity setDateBerth(LocalDate dateBirth) {
        this.dateBerth = dateBirth;
        return this;
    }

    public ImageEntity getImage() {
        return image;
    }

    public EmployeeEntity setImage(ImageEntity image) {
        this.image = image;
        return this;
    }

    public String getDepartmentRu() {
        return departmentRu;
    }

    public EmployeeEntity setDepartmentRu(String departmentRu) {
        this.departmentRu = departmentRu;
        return this;
    }

    public String getDepartmentKg() {
        return departmentKg;
    }

    public EmployeeEntity setDepartmentKg(String departmentKg) {
        this.departmentKg = departmentKg;
        return this;
    }

    public Active getActive() {
        return active;
    }

    public EmployeeEntity setActive(Active active) {
        this.active = active;
        return this;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public EmployeeEntity setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
        return this;
    }

    public LocalDate getDateDismissal() {
        return dateDismissal;
    }

    public EmployeeEntity setDateDismissal(LocalDate dateDismissal) {
        this.dateDismissal = dateDismissal;
        return this;
    }
}
