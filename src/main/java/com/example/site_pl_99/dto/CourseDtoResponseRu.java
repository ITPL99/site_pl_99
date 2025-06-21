package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.CourseType;

import java.time.LocalDate;

public class CourseDtoResponseRu {
    private CourseType courseType;
    private String titleRu;
    private String descriptionRu;
    private Double price;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private long imageId;

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
