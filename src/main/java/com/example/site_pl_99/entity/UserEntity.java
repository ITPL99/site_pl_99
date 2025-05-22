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


    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        dateUpdated = LocalDateTime.now();
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
}
