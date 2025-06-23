package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.CourseType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "course")
public class CourseEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private CourseType type;
    private String titleRu;
    private String titleKg;
    private String descriptionRu;
    private String descriptionKg;
    private Double price;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    public CourseType getType(){
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public void setTitleKg(String titleKg) {
        this.titleKg = titleKg;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionKg() {
        return descriptionKg;
    }

    public void setDescriptionKg(String descriptionKg) {
        this.descriptionKg = descriptionKg;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }
}
