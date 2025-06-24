package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.Active;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Ответ с информацией о преподавателе")
public class TeacherDtoResponse {

    @Schema(description = "Уникальный идентификатор преподавателя", example = "10")
    private Long id;

    @Schema(description = "Полное имя преподавателя", example = "Иван Иванов")
    private String fullName;

    @Schema(description = "Дата рождения преподавателя", example = "1980-05-21")
    private LocalDate dateBirth;

    @Schema(description = "Изображение преподавателя")
    private ImageDto image;

    @Schema(description = "Ссылка на портфолио преподавателя", example = "https://portfolio.example.com/ivan_ivanov")
    private String LinkPortfolio;

    @Schema(description = "Активный статус преподавателя", example = "ACTIVE")
    private Active active;

    @Schema(description = "Дата начала работы преподавателя", example = "2010-09-01")
    private LocalDate dateEmployment;

    @Schema(description = "Дата увольнения преподавателя", example = "2023-06-01", nullable = true)
    private LocalDate dateDismissal;

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
        return LinkPortfolio;
    }

    public void setLinkPortfolio(String linkPortfolio) {
        LinkPortfolio = linkPortfolio;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public void setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
    }

    public LocalDate getDateDismissal() {
        return dateDismissal;
    }

    public void setDateDismissal(LocalDate dateDismissal) {
        this.dateDismissal = dateDismissal;
    }

    public Long getId() {
        return id;
    }

    public TeacherDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }
}
