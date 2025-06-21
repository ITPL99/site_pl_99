package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VideoDto {
    private Long id;
    private String videoFileName;

    public Long getId() {
        return id;
    }

    public VideoDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public VideoDto setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
        return this;
    }
}
