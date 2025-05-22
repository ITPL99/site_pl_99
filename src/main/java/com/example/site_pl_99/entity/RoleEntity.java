package com.example.site_pl_99.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String title;

    @ManyToMany(mappedBy = "roleEntityList", fetch = FetchType.LAZY)
    private Set<UserEntity> users;

    public String getTitle() {
        return title;
    }

    public RoleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public RoleEntity setUsers(Set<UserEntity> users) {
        this.users = users;
        return this;
    }
}
