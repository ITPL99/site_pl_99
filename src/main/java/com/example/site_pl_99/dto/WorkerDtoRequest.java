package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Schema(description = "DTO для запроса информаций о работнике")
public class WorkerDtoRequest {
    @Schema(description = "Полное имя работника")
    private String fullName;
    @Schema(description = "Дата рождения работника")
    private LocalDate birthDate;
    @Schema(description = "Биография работника")
    private String biography;
    @Schema(description = "Профессия работника(охранник, мастер, учитель, уборщик")
    private String profession;

    public String getFullName() {
        return fullName;
    }

    public WorkerDtoRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public WorkerDtoRequest setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getBiography() {
        return biography;
    }

    public WorkerDtoRequest setBiography(String biography) {
        this.biography = biography;
        return this;
    }

    public String getProfession() {
        return profession;
    }

    public WorkerDtoRequest setProfession(String profession) {
        this.profession = profession;
        return this;
    }
}
