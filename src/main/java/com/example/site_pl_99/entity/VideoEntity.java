package com.example.site_pl_99.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class VideoEntity extends BaseEntity {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public VideoEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
