package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.Active;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity{
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_berth")
    private LocalDate dateBerth;
    @OneToOne
    @JoinColumn(name = "image_id",referencedColumnName = "id")
    private ImageEntity image;
    @Column(name = "department_ru")
    private String departmentRu;
    @Column(name = "department_kg")
    private String departmentKg;
    @Enumerated(EnumType.STRING)
    private Active active;
    @Column(name = "date_employment")
    private LocalDate dateEmployment;
    @Column(name = "date_dismissal")
    private LocalDate dateDismissal;

    @PrePersist
    public void prePersist(){
        active = Active.ACTIVE;
    }

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
