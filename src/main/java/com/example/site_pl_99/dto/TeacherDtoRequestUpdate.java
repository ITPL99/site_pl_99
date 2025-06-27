package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Запрос на обновление информации о преподавателе")
public class TeacherDtoRequestUpdate {

    @Schema(description = "Идентификатор преподавателя", example = "1", required = true)
    private Long id;

    @Schema(description = "Полное имя преподавателя", example = "Иван Иванов")
    private String fullName;

    @Schema(description = "Дата рождения преподавателя", example = "1980-05-21")
    private LocalDate dateBirth;

    @Schema(description = "Изображение преподавателя")
    private ImageDto image;

    @Schema(description = "Ссылка на портфолио преподавателя", example = "https://portfolio.example.com/ivan_ivanov")
    private String linkPortfolio;

    @Schema(description = "Дата начала работы преподавателя", example = "2010-09-01")
    private LocalDate dateEmployment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public String getLinkPortfolio() {
        return linkPortfolio;
    }

    public void setLinkPortfolio(String linkPortfolio) {
        this.linkPortfolio = linkPortfolio;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public void setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
    }
}
