package com.example.site_pl_99.dto;

public class WorkerDTO {
    private Long id;
    private String name;
    private Integer age;
    private String bio;

    public WorkerDTO() {
    }

    public WorkerDTO(Long id,
                     String name,
                     Integer age,
                     String bio) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bio = bio;
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
