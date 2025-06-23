package com.example.site_pl_99.dto;

import java.time.LocalDate;

public class CoursePreviewDto {
    private Long id;
    private String CourseType;
    private String title;
    private ImageDto image;
    private LocalDate dateCreated;
    private LocalDate dateEnd;
    private Double price;

    public Long getId() {
        return id;
    }

    public CoursePreviewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCourseType() {
        return CourseType;
    }

    public void setCourseType(String courseType) {
        CourseType = courseType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Double getPrice() {
        return price;
    }

    public CoursePreviewDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public CoursePreviewDto setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }
}
