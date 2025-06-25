package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.Active;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "teachers")
public class TeacherEntity extends BaseEntity{
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_berth")
    private LocalDate dateBerth;
    @OneToOne
    @JoinColumn(name = "image_id",referencedColumnName = "id")
    private ImageEntity image;
    @Column(name = "link_portfolio")
    private String linkPortfolio;
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
