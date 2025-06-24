package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.Active;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Объект ответа с информацией о мастере")
public class MasterDtoResponse {

    @Schema(description = "Полное имя мастера",
            example = "Александров Александр Аликсандрович")
    private String fullName;

    @Schema(description = "Дата рождения мастера",
            example = "1987-10-12")
    private LocalDate birthDate;

    @Schema(description = "Фотография мастера")
    private ImageDto image;

    @Schema(description = "Профессия мастера (одним полем)",
            example = "Backend-разработчик")
    private String profession;

    @Schema(description = "Статус активности мастера (ACTIVE / INACTIVE)")
    private Active active;

    @Schema(description = "Дата начала работы мастера",
            example = "2025-03-01")
    private LocalDate dateEmployment;

    @Schema(description = "Дата увольнения мастера, если применимо",
            example = "2025-03-02")
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
}
