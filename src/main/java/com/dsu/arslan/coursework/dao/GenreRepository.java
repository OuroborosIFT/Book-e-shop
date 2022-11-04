package com.dsu.arslan.coursework.dao;

import com.dsu.arslan.coursework.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
