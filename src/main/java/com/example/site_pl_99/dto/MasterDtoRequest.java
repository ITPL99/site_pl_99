package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MasterDtoRequest {
    private String fullName;
    private LocalDateTime birthday;
    private String profession;

    public String getFullName() {
        return fullName;
    }

    public MasterDtoRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public MasterDtoRequest setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getProfession() {
        return profession;
    }

    public MasterDtoRequest setProfession(String profession) {
        this.profession = profession;
        return this;
    }
}
