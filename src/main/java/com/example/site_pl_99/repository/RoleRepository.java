package com.example.site_pl_99.repository;


import com.example.site_pl_99.entity.RoleEntity;
import com.example.site_pl_99.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByTitle(String roleName);
    Optional<RoleEntity> findByUsers(UserEntity user);

}
