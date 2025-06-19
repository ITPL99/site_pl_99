package com.example.site_pl_99.repository;


import com.example.site_pl_99.entity.MasterEntity;
import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.enums.Active;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    Optional<NewsEntity> findByTitleRuOrTitleKg(String titleRu, String titleKg);
    Optional<Set<NewsEntity>> findAllByTitleRuContainingOrTitleKgContaining(String titleRu, String titleKg);
    Optional<List<NewsEntity>> findAllBySubTitleRuOrSubTitleKg(String subTitleRu, String subTitleKg);
    Optional<List<NewsEntity>> findAllByActive(Active active);
}
