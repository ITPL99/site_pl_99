package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.Active;

import java.time.LocalDate;

public class TeacherDtoResponse {
    private Long id;
    private String fullName;
    private LocalDate dateBirth;
    private ImageDto image;
    private String LinkPortfolio;
    private Active active;
    private LocalDate dateEmployment;
    private LocalDate dateDismissal;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public String getLinkPortfolio() {
        return LinkPortfolio;
    }

    public void setLinkPortfolio(String linkPortfolio) {
        LinkPortfolio = linkPortfolio;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public void setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
    }

    public LocalDate getDateDismissal() {
        return dateDismissal;
    }

    public void setDateDismissal(LocalDate dateDismissal) {
        this.dateDismissal = dateDismissal;
    }

    public Long getId() {
        return id;
    }

    public TeacherDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }
}
