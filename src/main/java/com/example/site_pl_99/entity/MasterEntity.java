package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.Active;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "masters")
public class MasterEntity extends BaseEntity {
    private String fullName;
    private LocalDate dateBerth;
    @OneToOne
    @JoinColumn(name = "image_id",referencedColumnName = "id")
    private ImageEntity image;
    private String professionKg;
    private String professionRu;
    @Enumerated(EnumType.STRING)
    private Active active;
    private LocalDate dateEmployment;
    private LocalDate dateDismissal;

    @PrePersist
    public void prePersist() {
        active = Active.NEW;
    }

    public String getFullName() {
        return fullName;
    }

    public MasterEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDate getDateBerth() {
        return dateBerth;
    }

    public MasterEntity setDateBerth(LocalDate dateBerth) {
        this.dateBerth = dateBerth;
        return this;
    }

    public ImageEntity getImage() {
        return image;
    }

    public MasterEntity setImage(ImageEntity image) {
        this.image = image;
        return this;
    }

    public String getProfessionKg() {
        return professionKg;
    }

    public MasterEntity setProfessionKg(String professionKg) {
        this.professionKg = professionKg;
        return this;
    }

    public String getProfessionRu() {
        return professionRu;
    }

    public MasterEntity setProfessionRu(String professionRu) {
        this.professionRu = professionRu;
        return this;
    }

    public Active getActive() {
        return active;
    }

    public MasterEntity setActive(Active active) {
        this.active = active;
        return this;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public MasterEntity setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
        return this;
    }

    public LocalDate getDateDismissal() {
        return dateDismissal;
    }

    public MasterEntity setDateDismissal(LocalDate dateDismissal) {
        this.dateDismissal = dateDismissal;
        return this;
    }
}
