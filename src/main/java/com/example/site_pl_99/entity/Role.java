package com.example.site_pl_99.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String role;
    @ManyToMany(mappedBy = "roleUsersList", fetch = FetchType.LAZY)
    private List<User> user;

    public Role() {
    }

    public Role(List<User> user, Long id, String role) {
        this.user = user;
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Role setRole(String role) {
        this.role = role;
        return this;
    }

    public List<User> getUser() {
        return user;
    }

    public Role setUser(List<User> user) {
        this.user = user;
        return this;
    }
}
