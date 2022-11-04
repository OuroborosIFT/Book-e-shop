package com.dsu.arslan.coursework.dao;

import com.dsu.arslan.coursework.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
