package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.News;
import com.example.site_pl_99.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findNewsByTitle(String title);
    Optional<News> findNewsByDateCreated(LocalDateTime dateCreated);
    Optional<List<News>> findNewsByUser(User user);
}
