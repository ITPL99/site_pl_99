package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Объект запроса для мастера")
public class MasterDtoRequest {

    @Schema(description = "Полное имя мастера",
            example = "Александров Александр Аликсандрович")
    private String fullName;

    @Schema(description = "Дата рождения мастера в формате ГГГГ-ММ-ДД",
            example = "1985-03-22")
    private LocalDate birthDate;

    @Schema(description = "Профессия на кыргызском языке",
            example = "Программист")
    private String professionKg;

    @Schema(description = "Профессия на русском языке",
            example = "Программист")
    private String professionRu;

    @Schema(description = "Фотография мастера")
    private ImageDto image;

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