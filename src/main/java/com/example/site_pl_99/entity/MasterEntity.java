package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.ActiveJob;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "masters")
@RequiredArgsConstructor
public class MasterEntity extends BaseEntity {
    private String fullName;
    private LocalDateTime dateOfBirth;
    private String profession;
    private ActiveJob active;
    private LocalDateTime dateStarted;
    private LocalDateTime dateFired;
    @OneToOne(mappedBy = "masterEntity", fetch = FetchType.EAGER)
    private ImageMasterEntity image;

    public String getFullName() {
        return fullName;
    }

    public MasterEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public MasterEntity setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getProfession() {
        return profession;
    }

    public MasterEntity setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public ActiveJob getActive() {
        return active;
    }

    public MasterEntity setActive(ActiveJob active) {
        this.active = active;
        return this;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public MasterEntity setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
        return this;
    }

    public LocalDateTime getDateFired() {
        return dateFired;
    }

    public MasterEntity setDateFired(LocalDateTime dateFired) {
        this.dateFired = dateFired;
        return this;
    }

    public ImageMasterEntity getImage() {
        return image;
    }

    public MasterEntity setImage(ImageMasterEntity image) {
        this.image = image;
        return this;
    }
}
