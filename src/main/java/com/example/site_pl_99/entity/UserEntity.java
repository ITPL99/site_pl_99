package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
public class UserEntity extends BaseEntity implements UserDetails {
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "active")
    private String active;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @ManyToMany(mappedBy = "userEntityList",fetch = FetchType.EAGER)
    private List<RoleEntity> roles;

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getActive() {
        return active;
    }

    public UserEntity setActive(String active) {
        this.active = active;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
