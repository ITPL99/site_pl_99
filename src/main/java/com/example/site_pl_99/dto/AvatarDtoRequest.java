package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AvatarDtoRequest {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public AvatarDtoRequest setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
