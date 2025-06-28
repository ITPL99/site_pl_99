package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainMenuDtoRequest {
    private String titleRu;
    private String titleKg;
    private String subTitleRu;
    private String subTitleKg;
    private Long amountStudents;
    private Long amountGraduates;
    private Long amountPartners;
    private ImageDto image;

    public String getTitleRu() {
        return titleRu;
    }

    public MainMenuDtoRequest setTitleRu(String titleRu) {
        this.titleRu = titleRu;
        return this;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public MainMenuDtoRequest setTitleKg(String titleKg) {
        this.titleKg = titleKg;
        return this;
    }

    public String getSubTitleRu() {
        return subTitleRu;
    }

    public MainMenuDtoRequest setSubTitleRu(String subTitleRu) {
        this.subTitleRu = subTitleRu;
        return this;
    }

    public String getSubTitleKg() {
        return subTitleKg;
    }

    public MainMenuDtoRequest setSubTitleKg(String subTitleKg) {
        this.subTitleKg = subTitleKg;
        return this;
    }

    public Long getAmountStudents() {
        return amountStudents;
    }

    public MainMenuDtoRequest setAmountStudents(Long amountStudents) {
        this.amountStudents = amountStudents;
        return this;
    }

    public Long getAmountGraduates() {
        return amountGraduates;
    }

    public MainMenuDtoRequest setAmountGraduates(Long amountGraduates) {
        this.amountGraduates = amountGraduates;
        return this;
    }

    public Long getAmountPartners() {
        return amountPartners;
    }

    public MainMenuDtoRequest setAmountPartners(Long amountPartners) {
        this.amountPartners = amountPartners;
        return this;
    }

    public ImageDto getImage() {
        return image;
    }

    public MainMenuDtoRequest setImage(ImageDto image) {
        this.image = image;
        return this;
    }
}
