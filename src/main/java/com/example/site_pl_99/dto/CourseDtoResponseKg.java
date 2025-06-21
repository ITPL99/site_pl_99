package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.CourseType;

import java.time.LocalDate;

public class CourseDtoResponseKg {
    private CourseType courseType;
    private String titleKg;
    private String descriptionKg;
    private Double price;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private long imageId;

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public void setTitleKg(String titleKg) {
        this.titleKg = titleKg;
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

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}
