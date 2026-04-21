package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByActiveCode(String activeCode);
//    Optional<List<UserEntity>> findByDateCreated(OffsetDateTime dateTimeCreate);
//    Optional<List<UserEntity>> findByDateUpdated(OffsetDateTime dateTimeUpdate);
//    Optional<List<UserEntity>> findAllByRoleEntityList(RoleEntity roleEntity);
}
