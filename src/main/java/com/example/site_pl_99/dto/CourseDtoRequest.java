package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.Active;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Запрос для создания или обновления курса")
public class CourseDtoRequest {

    @Schema(description = "Тип курса (например: online, offline)", example = "online")
    private String courseType;

    @Schema(description = "Название курса на кыргызском языке", example = "Java Окутуу Курсу")
    private String titleKg;

    @Schema(description = "Название курса на русском языке", example = "Курс по обучению Java")
    private String titleRu;

    @Schema(description = "Описание курса на кыргызском языке", example = "Бул курс Java тилин үйрөнүүгө арналган.")
    private String descriptionKg;

    @Schema(description = "Описание курса на русском языке", example = "Курс предназначен для изучения языка Java.")
    private String descriptionRu;

    @Schema(description = "Цена курса", example = "1999.99")
    private Double price;

    @Schema(description = "Изображение курса в виде DTO объекта")
    private ImageDto image;

    @Schema(description = "Дата начала курса", example = "2025-07-01", type = "string", format = "date")
    private LocalDate dateStart;

    @Schema(description = "Дата окончания курса", example = "2025-08-01", type = "string", format = "date")
    private LocalDate dateEnd;

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public void setTitleKg(String titleKg) {
        this.titleKg = titleKg;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getDescriptionKg() {
        return descriptionKg;
    }

    public void setDescriptionKg(String descriptionKg) {
        this.descriptionKg = descriptionKg;
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

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
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
}
