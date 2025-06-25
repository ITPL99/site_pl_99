package com.example.site_pl_99.repository;


import com.example.site_pl_99.entity.MasterEntity;
import com.example.site_pl_99.enums.Active;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MasterRepository extends JpaRepository<MasterEntity, Long> {
    Optional<MasterEntity> findByFullName(String fullName);
    List<MasterEntity> findAllByFullNameContaining(String fullName);
    List<MasterEntity> findAllByDateBerth(LocalDate berth);
    List<MasterEntity> findAllByActive(Active active);
    List<MasterEntity> findAllByDateEmployment(LocalDate dateEmployment);
    List<MasterEntity> findAllByDateDismissal(LocalDate dateDismissal);
    List<MasterEntity> findAllByProfessionRuOrProfessionKg(String professionRu, String professionKg);
}
