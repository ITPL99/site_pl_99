package com.example.site_pl_99.dto;

import java.time.LocalDate;

public class MasterDtoRequestUpdate {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String professionKg;
    private String professionRu;
    private ImageDto image;
    private LocalDate dateEmployment;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfessionKg() {
        return professionKg;
    }

    public void setProfessionKg(String professionKg) {
        this.professionKg = professionKg;
    }

    public String getProfessionRu() {
        return professionRu;
    }

    public void setProfessionRu(String professionRu) {
        this.professionRu = professionRu;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public void setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
    }
}
