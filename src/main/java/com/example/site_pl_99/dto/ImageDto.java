package com.example.site_pl_99.dto;

public class ImageDto {
    private Long Id;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public Long getId() {
        return Id;
    }

    public ImageDto setId(Long id) {
        Id = id;
        return this;
    }

    public ImageDto setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
