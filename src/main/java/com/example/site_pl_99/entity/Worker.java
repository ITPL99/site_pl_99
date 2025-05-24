package com.example.site_pl_99.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "workers")
public class Worker extends BaseEntity{
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private String bio;
    @Column
    private String profession;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "m2m_qualifications_workers",
            joinColumns = @JoinColumn(name = "worker_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id", referencedColumnName = "id")
    )
    private List<Qualification> qualifications;

    public Worker() {
    }

    public Worker
            (Long id,
                  String name,
                  Integer age,
                  String bio,
                  String profession,
                  List<Qualification> qualifications) {
        this.profession = profession;
        this.id = id;
        this.name = name;
        this.age = age;
        this.bio = bio;
        this.qualifications = qualifications;
    }

    public String getProfession() {
        return profession;
    }

    public Worker setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public String getName() {
        return name;
    }

    public Worker setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Worker setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public Worker setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public Worker setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
        return this;
    }
}
