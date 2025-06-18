package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.CourseType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "video_content")
public class CourseEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private CourseType type;
    private String titleRu;
    private String titleKg;
    private String descriptionRu;
    private String descriptionKg;
    private Double price;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;
}
