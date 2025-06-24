package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Объект превью курса для отображения краткой информации")
public class CoursePreviewDto {

    @Schema(description = "Тип курса ",
            example = "SPORT")
    private String CourseType;

    @Schema(description = "Название курса",
            example = "Введение в Java")
    private String title;

    @Schema(description = "Изображение, связанное с курсом")
    private ImageDto image;

    @Schema(description = "Дата создания курса",
            example = "2024-05-15")
    private LocalDate dateCreated;

    @Schema(description = "Цена курса",
            example = "15000")
    private int price;


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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
