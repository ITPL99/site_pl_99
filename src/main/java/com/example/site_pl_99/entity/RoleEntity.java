package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "roles")
@RequiredArgsConstructor
public class RoleEntity extends BaseEntity implements GrantedAuthority {
    private String roleName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<UserEntity> userEntityList;

    @Override
    public String getAuthority() {
        return roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public RoleEntity setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public RoleEntity setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
        return this;
    }
}
