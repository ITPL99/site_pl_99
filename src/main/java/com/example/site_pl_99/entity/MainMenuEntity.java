package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "main_menu")
@RequiredArgsConstructor
public class MainMenuEntity extends BaseEntity{
    @Column(name = "title_ru", nullable = false)
    private String titleRu;
    @Column(name = "title_kg", nullable = false)
    private String titleKg;
    @Column(name = "subtitle_ru", nullable = false)
    private String subTitleRu;
    @Column(name = "subtitle_kg", nullable = false)
    private String subTitleKg;
    @Column(name = "amount_students")
    private Long amountStudents;
    @Column(name = "amount_graduated")
    private Long amountGraduates;
    @Column(name = "amount_partners")
    private Long amountPartners;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageEntity image;

    @PrePersist
    public void prePersist(){
        amountStudents = 0l;
        amountGraduates = 0l;
        amountPartners = 0l;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public MainMenuEntity setTitleRu(String titleRu) {
        this.titleRu = titleRu;
        return this;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public MainMenuEntity setTitleKg(String titleKg) {
        this.titleKg = titleKg;
        return this;
    }

    public String getSubTitleRu() {
        return subTitleRu;
    }

    public MainMenuEntity setSubTitleRu(String subTitleRu) {
        this.subTitleRu = subTitleRu;
        return this;
    }

    public String getSubTitleKg() {
        return subTitleKg;
    }

    public MainMenuEntity setSubTitleKg(String subTitleKg) {
        this.subTitleKg = subTitleKg;
        return this;
    }

    public Long getAmountStudents() {
        return amountStudents;
    }

    public MainMenuEntity setAmountStudents(Long amountStudents) {
        this.amountStudents = amountStudents;
        return this;
    }

    public Long getAmountGraduates() {
        return amountGraduates;
    }

    public MainMenuEntity setAmountGraduates(Long amountGraduates) {
        this.amountGraduates = amountGraduates;
        return this;
    }

    public Long getAmountPartners() {
        return amountPartners;
    }

    public MainMenuEntity setAmountPartners(Long amountPartners) {
        this.amountPartners = amountPartners;
        return this;
    }

    public ImageEntity getImage() {
        return image;
    }

    public MainMenuEntity setImage(ImageEntity image) {
        this.image = image;
        return this;
    }
}
