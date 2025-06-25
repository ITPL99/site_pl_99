package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.Active;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "DTO ответа с информацией о мастере")
public class MasterDtoResponse {

    @Schema(description = "Уникальный идентификатор мастера", example = "1")
    private Long id;

    @Schema(description = "Полное имя мастера", example = "Иванов Иван Иванович")
    private String fullName;

    @Schema(description = "Дата рождения мастера", example = "1980-12-31", type = "string", format = "date")
    private LocalDate birthDate;

    @Schema(description = "Изображение мастера")
    private ImageDto image;

    @Schema(description = "Профессия мастера", example = "Мастер по ремонту")
    private String profession;

    @Schema(description = "Активный статус мастера", example = "ACTIVE")
    private Active active;

    @Schema(description = "Дата начала трудовой деятельности", example = "2020-01-01", type = "string", format = "date")
    private LocalDate dateEmployment;

    @Schema(description = "Дата увольнения мастера (если применимо)", example = "2023-05-15", type = "string", format = "date")
    private LocalDate dateDismissal;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

    public MasterDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }
}
