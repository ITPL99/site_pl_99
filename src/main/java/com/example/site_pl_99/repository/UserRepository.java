package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.RoleEntity;
import com.example.site_pl_99.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestMapping
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<List<UserEntity>> findByDateCreated(LocalDateTime dateTimeCreate);
    Optional<List<UserEntity>> findByDateUpdated(LocalDateTime dateTimeUpdate);
    Optional<List<UserEntity>> findAllByRoleEntityList(RoleEntity roleEntity);
}
