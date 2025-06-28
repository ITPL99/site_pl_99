package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainMenuDtoResponse {
    private Long id;
    private String title;
    private String subTitle;
    private Long amountStudents;
    private Long amountGraduates;
    private Long amountPartners;
    private ImageDto image;

    public Long getId() {
        return id;
    }

    public MainMenuDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MainMenuDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public MainMenuDtoResponse setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public Long getAmountStudents() {
        return amountStudents;
    }

    public MainMenuDtoResponse setAmountStudents(Long amountStudents) {
        this.amountStudents = amountStudents;
        return this;
    }

    public Long getAmountGraduates() {
        return amountGraduates;
    }

    public MainMenuDtoResponse setAmountGraduates(Long amountGraduates) {
        this.amountGraduates = amountGraduates;
        return this;
    }

    public Long getAmountPartners() {
        return amountPartners;
    }

    public MainMenuDtoResponse setAmountPartners(Long amountPartners) {
        this.amountPartners = amountPartners;
        return this;
    }

    public ImageDto getImage() {
        return image;
    }

    public MainMenuDtoResponse setImage(ImageDto image) {
        this.image = image;
        return this;
    }
}
