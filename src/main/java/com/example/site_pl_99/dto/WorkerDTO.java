package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.Worker;

import java.util.List;

public class WorkerDTO {
    private Long id;
    private String name;
    private Integer age;
    private String bio;
    private String profession;
    private List<QualificationDTO> qualifications;

    public WorkerDTO() {
    }

    public WorkerDTO(Worker worker) {
        this.id = worker.getId();
        this.name = worker.getName();
        this.age = worker.getAge();
        this.bio = worker.getBio();
        this.profession = worker.getProfession();
        this.qualifications = worker.getQualifications().stream().map(QualificationDTO::new).toList();
    }

    public String getProfession() {
        return profession;
    }

    public WorkerDTO setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public List<QualificationDTO> getQualifications() {
        return qualifications;
    }

    public WorkerDTO setQualifications(List<QualificationDTO> qualifications) {
        this.qualifications = qualifications;
        return this;
    }

    public Long getId() {
        return id;
    }

    public WorkerDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkerDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public WorkerDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public WorkerDTO setBio(String bio) {
        this.bio = bio;
        return this;
    }
}
