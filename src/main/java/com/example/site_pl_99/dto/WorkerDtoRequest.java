package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class WorkerDtoRequest {
    private String fullName;
    private LocalDate birthDate;
    private String biography;
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
