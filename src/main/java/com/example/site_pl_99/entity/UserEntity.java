package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "active")
    private Boolean active;

    @Column(name = "date_create")
    private LocalDateTime dateCreated;

    @Column(name = "date_update")
    private LocalDateTime dateUpdated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "m2m_users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<RoleEntity> roleEntityList;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<NewsEntity> newsEntityList;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<WorkerEntity> workerEntityList;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<CourseEntity> courseEntityList;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<QualificationEntity> qualificationEntityList;


    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        dateUpdated = LocalDateTime.now();
        active = true;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleEntityList;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public UserEntity setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public UserEntity setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public List<RoleEntity> getRoleEntityList() {
        return roleEntityList;
    }

    public UserEntity setRoleEntityList(List<RoleEntity> roleEntityList) {
        this.roleEntityList = roleEntityList;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public UserEntity setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public UserEntity setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public List<NewsEntity> getNewsEntityList() {
        return newsEntityList;
    }

    public UserEntity setNewsEntityList(List<NewsEntity> newsEntityList) {
        this.newsEntityList = newsEntityList;
        return this;
    }

    public List<WorkerEntity> getWorkerEntityList() {
        return workerEntityList;
    }

    public UserEntity setWorkerEntityList(List<WorkerEntity> workerEntityList) {
        this.workerEntityList = workerEntityList;
        return this;
    }

    public List<CourseEntity> getCourseEntityList() {
        return courseEntityList;
    }

    public UserEntity setCourseEntityList(List<CourseEntity> courseEntityList) {
        this.courseEntityList = courseEntityList;
        return this;
    }

    public List<QualificationEntity> getQualificationEntityList() {
        return qualificationEntityList;
    }

    public UserEntity setQualificationEntityList(List<QualificationEntity> qualificationEntityList) {
        this.qualificationEntityList = qualificationEntityList;
        return this;
    }
}
