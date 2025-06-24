package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.CourseType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Объект запроса по модели курса")
public class CourseDtoRequest {

    @Schema(description = "Тип курса ",
            example = "SPORT")
    private CourseType courseType;

    @Schema(description = "Название курса на кыргызском языке")
    private String titleKg;

    @Schema(description = "Название курса на русском языке",
            example = "Java для Начинающих")
    private String titleRu;

    @Schema(description = "Полное описание курса на кыргызском языке",
            example = "Бул курс Java программалоо тилин нөлдөн баштап үйрөнүүгө арналган. Практикалык тапшырмалар аркылуу негизги концепцияларды өздөштүрөсүз.")
    private String descriptionKg;

    @Schema(description = "Полное описание курса на русском языке",
            example = "Этот курс предназначен для изучения языка программирования Java с нуля. Вы освоите основные концепции через практические задания.")
    private String descriptionRu;

    @Schema(description = "Стоимость курса",
            example = "25000.00")
    private Double price;

    @Schema(description = "изображение курса")
    private ImageDto image;

    @Schema(description = "Дата начала курса в формате ГГГГ-ММ-ДД",
            example = "2025-09-01")
    private LocalDate dateStart;

    @Schema(description = "Дата окончания курса в формате ГГГГ-ММ-ДД",
            example = "2025-12-31")
    private LocalDate dateEnd;

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