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
    @Column(unique = true)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "m2m_qualifications_workers",
            joinColumns = @JoinColumn(name = "qualification_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id", referencedColumnName = "id")
    )
    private List<Qualification> qualification;

    public Worker() {
    }

    public Worker
            (Long id,
                  String name,
                  Integer age,
                  String bio,
                  List<Qualification> qualification) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bio = bio;
        this.qualification = qualification;
    }

    public Long getId() {
        return id;
    }

    public Worker setId(Long id) {
        this.id = id;
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

    public List<Qualification> getQualification() {
        return qualification;
    }

    public Worker setQualification(List<Qualification> qualification) {
        this.qualification = qualification;
        return this;
    }
}
