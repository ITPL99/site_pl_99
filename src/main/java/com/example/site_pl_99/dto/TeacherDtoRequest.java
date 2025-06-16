package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TeacherDtoRequest {
    private String fullName;
    private LocalDateTime birthday;
    private String linkPortfolio;

    public String getFullName() {
        return fullName;
    }

    public TeacherDtoRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public TeacherDtoRequest setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getLinkPortfolio() {
        return linkPortfolio;
    }

    public TeacherDtoRequest setLinkPortfolio(String linkPortfolio) {
        this.linkPortfolio = linkPortfolio;
        return this;
    }
}
