package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.Active;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "teachers")
public class TeacherEntity extends BaseEntity{
    private String fullName;
    private LocalDate dateBerth;
    @OneToOne
    @JoinColumn(name = "image_id",referencedColumnName = "id")
    private ImageEntity image;
    private String linkPortfolio;
    @Enumerated(EnumType.STRING)
    private Active active;
    private LocalDate dateEmployment;
    private LocalDate dateDismissal;

    public String getFullName() {
        return fullName;
    }

    public TeacherEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDate getDateBerth() {
        return dateBerth;
    }

    public TeacherEntity setDateBerth(LocalDate dateBerth) {
        this.dateBerth = dateBerth;
        return this;
    }

    public ImageEntity getImage() {
        return image;
    }

    public TeacherEntity setImage(ImageEntity image) {
        this.image = image;
        return this;
    }

    public String getLinkPortfolio() {
        return linkPortfolio;
    }

    public TeacherEntity setLinkPortfolio(String linkPortfolio) {
        this.linkPortfolio = linkPortfolio;
        return this;
    }

    public Active getActive() {
        return active;
    }

    public TeacherEntity setActive(Active active) {
        this.active = active;
        return this;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public TeacherEntity setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
        return this;
    }

    public LocalDate getDateDismissal() {
        return dateDismissal;
    }

    public TeacherEntity setDateDismissal(LocalDate dateDismissal) {
        this.dateDismissal = dateDismissal;
        return this;
    }
}
