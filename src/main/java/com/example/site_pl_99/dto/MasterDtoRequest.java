package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "DTO запроса на создание или обновление мастера")
public class MasterDtoRequest {

    @Schema(description = "Полное имя мастера", example = "Иванов Иван Иванович", required = true)
    private String fullName;

    @Schema(description = "Дата рождения мастера", example = "1980-12-31", type = "string", format = "date", required = true)
    private LocalDate birthDate;

    @Schema(description = "Профессия на кыргызском языке", example = "Уста")
    private String professionKg;

    @Schema(description = "Профессия на русском языке", example = "Мастер")
    private String professionRu;

    @Schema(description = "Изображение мастера (DTO)")
    private ImageDto image;

    @Schema(description = "Дата начала трудовой деятельности", example = "2020-01-01", type = "string", format = "date")
    private LocalDate dateEmployment;

    public LocalDate getDateEmployment() {
        return dateEmployment;
    }

    public void setDateEmployment(LocalDate dateEmployment) {
        this.dateEmployment = dateEmployment;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

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

    public String getProfessionKg() {
        return professionKg;
    }

    public void setProfessionKg(String professionKg) {
        this.professionKg = professionKg;
    }

    public String getProfessionRu() {
        return professionRu;
    }

    public void setProfessionRu(String professionRu) {
        this.professionRu = professionRu;
    }
}
