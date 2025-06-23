package com.example.site_pl_99.dto;

import java.time.LocalDate;

public class MasterDtoRequest {
    private String fullName;
    private LocalDate birthDate;
    private String professionKg;
    private String professionRu;
    private ImageDto image;

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
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
}
