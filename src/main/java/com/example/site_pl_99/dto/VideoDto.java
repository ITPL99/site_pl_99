package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VideoDto {
    private Long id;
    private String FileName;

    public Long getId() {
        return id;
    }

    public VideoDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return FileName;
    }

    public VideoDto setFileName(String fileName) {
        this.FileName = fileName;
        return this;
    }
}
