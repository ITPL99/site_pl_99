package com.example.site_pl_99.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name =  "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String login;
    @Column()
    private String password;
    @Column()
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "m2m_users_roles",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<Role> role;
    @Column
    private LocalDateTime dataCreated;
    @Column
    private LocalDateTime dataUpdated;

    public User() {
    }

    public User(
            List<Role> role,
            Long id,
            String login,
            String password,
            String email,
            LocalDateTime dataCreated,
            LocalDateTime dataUpdated) {
        this.role = role;
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.dataCreated = dataCreated;
        this.dataUpdated = dataUpdated;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDateTime getDataCreated() {
        return dataCreated;
    }

    public User setDataCreated(LocalDateTime dataCreated) {
        this.dataCreated = dataCreated;
        return this;
    }

    public LocalDateTime getDataUpdated() {
        return dataUpdated;
    }

    public User setDataUpdated(LocalDateTime dataUpdated) {
        this.dataUpdated = dataUpdated;
        return this;
    }

    public List<Role> getRole() {
        return role;
    }

    public User setRole(List<Role> role) {
        this.role = role;
        return this;
    }
}
