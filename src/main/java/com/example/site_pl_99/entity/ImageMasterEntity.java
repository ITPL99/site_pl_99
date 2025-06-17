package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.ActiveJob;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "images_masters")
@RequiredArgsConstructor
public class ImageMasterEntity extends BaseEntity {
    private String fileName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id", referencedColumnName = "id", unique = true)
    private MasterEntity masterEntity;
    @Column(name = "active")
    private ActiveJob active;

    public ActiveJob getActive() {
        return active;
    }

    public void setActive(ActiveJob active) {
        this.active = active;
    }

    public String getFileName() {
        return fileName;
    }

    public ImageMasterEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public MasterEntity getMasterEntity() {
        return masterEntity;
    }

    public ImageMasterEntity setMasterEntity(MasterEntity masterEntity) {
        this.masterEntity = masterEntity;
        return this;
    }
}
